package pers.alex.demo.versions;

import pers.alex.util.upgrade.UpgradeInstanse;
import pers.alex.util.upgrade.UpgradeVersion;

/**
 * 执行成功
 * @author Alex
 * @date 12/2/2020 4:09 PM
 */
@UpgradeVersion(value = "04", desc = "版本4")
public class Version04 implements UpgradeInstanse {

    @Override
    public boolean execute() {



        return true;
    }
}
