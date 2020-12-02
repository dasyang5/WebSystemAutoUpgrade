package pers.alex.util.upgrade;

/**
 * @author Alex
 * @date 12/1/2020 11:15 AM
 */
public interface UpgradeInstanse {

    /**
     * 具体的执行方法
     * @return true: 执行成功  false: 执行失败
     */
    boolean execute();

}
