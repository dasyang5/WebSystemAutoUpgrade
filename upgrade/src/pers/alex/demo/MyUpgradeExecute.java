package pers.alex.demo;

import pers.alex.util.upgrade.UpgradeExecute;

/**
 * @author Alex
 * @date 12/2/2020 3:56 PM
 */
public class MyUpgradeExecute extends UpgradeExecute {
    @Override
    protected String getPackageName() {
        return "pers.alex.demo.versions";
    }

    @Override
    protected String getCurrentVersion() {
        return "01";
    }

    @Override
    protected int compareToVersion(String s1, String s2) {
        return s1.compareTo(s2);
    }

    @Override
    protected void log(String info) {
        System.out.println(info);
    }


    public static void main(String[] args) {
        //执行自动升级
        new MyUpgradeExecute().executeAll();
    }
}
