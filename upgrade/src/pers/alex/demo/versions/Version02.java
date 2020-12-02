package pers.alex.demo.versions;

import pers.alex.util.upgrade.UpgradeInstanse;
import pers.alex.util.upgrade.UpgradeVersion;

/**
 * 版本高于当前版本，则执行升级
 *
 * @author Alex
 * @date 12/2/2020 4:09 PM
 */
@UpgradeVersion(value = "02", desc = "版本2")
public class Version02 implements UpgradeInstanse {

    @Override
    public boolean execute() {



        return true;
    }
}
