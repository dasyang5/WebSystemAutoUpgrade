# WebSystemAutoUpgrade
Web系统每次升级，总会附带执行一些脚本语句，这个工具类可以帮忙实现自动升级。

### 使用方法
继承UpgradeExecute类，实现getPackageName()、getCurrentVersion()、compareToVersion()、log()方法。
```
public class MyUpgradeExecute extends UpgradeExecute {
    @Override
    protected String getPackageName() {
        //版本类存放的包名，系统会扫描该包下的所有类
        return "pers.alex.demo.versions";
    }

    @Override
    protected String getCurrentVersion() {
        //系统当前的版本号，一般存在数据库里，每次从库里取出来，升级完，再更新库里的值。
        return "01";
    }

    @Override
    protected int compareToVersion(String s1, String s2) {
        //自定义版本号比较方法
        return s1.compareTo(s2);
    }

    @Override
    protected void log(String info) {
        //自定义日志输出
        System.out.println(info);
    }


    public static void main(String[] args) {
        //执行自动升级
        new MyUpgradeExecute().executeAll();
    }
}
```

### 执行结果：
-----------------------------------------
Start, current version: 01
-----------------------------------------
Version: 01
Description: 版本1
Result:  - 跳过升级!
-----------------------------------------
Version: 02
Description: 版本2
Result:  - 升级成功!
-----------------------------------------
Version: 03
Description: 版本3
Result:  - 未实现UpgradeExecute接口!
-----------------------------------------
Version: 04
Description: 版本4
Result:  - 升级成功!
-----------------------------------------
Version: 05
Description: 版本5
Result:  - 升级失败，停止升级!
-----------------------------------------
Version: 06
Description: 版本6
Result:  - 已终止!
-----------------------------------------
End, final version: 04
-----------------------------------------
### 执行结果描述
- 跳过升级：如果待升级版本低于当前版本
- 升级失败：执行结果返回false，此后的所有版本都会终止执行
- 已终止：前面的版本升级失败，后面版本都不会再执行升级
- 升级成功：执行结果返回true
- 已禁用：升级类已停用，UpgradeVersion注解的disable值为true
