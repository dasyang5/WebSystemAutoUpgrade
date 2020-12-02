package pers.alex.util.upgrade;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自动升级系统
 *
 * @author Alex
 * @date 12/1/2020 11:18 AM
 */
public abstract class UpgradeExecute {

    private static List<ClassWrapper> classWrapperList;

    private static boolean isContinue = true;

    private String finalVersion = null;

    /**
     * 系统升级
     *
     * @return 最终系统版本
     */
    public String executeAll() {

        classWrapperList = new ArrayList<>();

        String packageName = getPackageName();
        String currentVersion = getCurrentVersion();

        if (isEmpty(packageName)) {
            throw new IllegalArgumentException("Package name can not be null!");
        }

        if (isEmpty(currentVersion)) {
            throw new IllegalArgumentException("Current version can not be null!");
        }

        scanPackage(packageName);

        doUpgrade(currentVersion);

        return finalVersion;
    }

    /**
     * 扫描包 并且将符合结果的类假如到list中进行处理
     *
     * @param packageName 包名称
     */
    private void scanPackage(String packageName) {
        //扫描包
        new PackageScanner() {
            @Override
            public void dealClass(Class<?> clazz) {
                UpgradeVersion upgradeVersion = clazz.getAnnotation(UpgradeVersion.class);

                if (upgradeVersion != null) {
                    String value = upgradeVersion.value();
                    String desc = upgradeVersion.desc();
                    boolean disable = upgradeVersion.disable();

                    ClassWrapper classWrapper = new ClassWrapper(clazz, value, desc, disable);

                    classWrapperList.add(classWrapper);
                }

            }

        }.packageScan(packageName);
    }

    /**
     * 遍历并且执行升级过程
     *
     *  跳过升级：如果待升级版本低于当前版本
     *  升级失败：执行结果返回false，此后的所有版本都会终止执行
     *  已终止：前面的版本升级失败，后面版本都不会再执行升级
     *  升级成功：执行结果返回true
     *  已禁用：升级类已停用，UpgradeVersion注解的disable值为true
     *
     * @param currentVersion 当前系统版本
     */
    private void doUpgrade(String currentVersion) {
        log("-----------------------------------------");
        log("Start, current version: " + currentVersion);

        classWrapperList
                .stream()
                //排序并且遍历
                .sorted((a,b)-> compareToVersion(a.getValue(), b.getValue())).forEach(wrapper->{

                    log("-----------------------------------------");
                    log("Version: " + wrapper.getValue());
                    log("Description: " + wrapper.getDesc());

                    if(!isContinue){
                        log("Result: " + " - 已终止!");
                    }else if (wrapper.isDisable()) {
                        log("Result: " + " - 已禁用!");
                    } else if (compareToVersion(wrapper.getValue(), currentVersion) > 0) {
                        try {
                            Class clazz = wrapper.getClazz();

                            if (Arrays.stream(clazz.getInterfaces()).anyMatch(c -> c == UpgradeInstanse.class)) {
                                Method method = clazz.getMethod("execute");

                                boolean result = (boolean) method.invoke(clazz.newInstance());

                                if (result) {
                                    finalVersion = wrapper.getValue();

                                    log("Result: " + " - 升级成功!");
                                } else {
                                    log("Result: " + " - 升级失败，停止升级!");
                                    isContinue = false;
                                }
                            } else {
                                log("Result: " + " - 未实现UpgradeExecute接口!");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            log("Exception: " + e.getMessage());
                        }

                    } else {
                        log("Result: " + " - 跳过升级!");
                    }
        });

        log("-----------------------------------------");
        log("End, final version: " + finalVersion);
        log("-----------------------------------------");
    }

    /**
     * 判断
     *
     * @param s 待判断字符串
     *
     * @return true：空  false：非空
     */
    private boolean isEmpty(String s) {
        return null == s || s.length() <= 0;
    }

    /**
     * 扫描的包名
     * 该包将包括所有升级类
     *
     * @return 包名
     */
    protected abstract String getPackageName();

    /**
     * @return 当前系统版本号
     */
    protected abstract String getCurrentVersion();

    /**
     * 两个版本比较大小
     *
     * @param s1 版本1
     * @param s2 版本2
     * @return 返回正数  说明s1 > s2  返回负数  说明s1 < s2  返回0 说明s1 = s2
     */
    protected abstract int compareToVersion(String s1, String s2);

    /**
     * 自定义日志输出
     *
     * @param info 日志信息
     */
    protected abstract void log(String info);
}
