package pers.alex.demo.versions;

import pers.alex.util.upgrade.UpgradeInstanse;
import pers.alex.util.upgrade.UpgradeVersion;

/**
 * execute方法返回值为false 表示执行失败
 * @author Alex
 * @date 12/2/2020 4:09 PM
 */
@UpgradeVersion(value = "05", desc = "版本5")
public class Version05 implements UpgradeInstanse {

    @Override
    public boolean execute() {
        return false;
    }
}
