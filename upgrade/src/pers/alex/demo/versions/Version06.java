package pers.alex.demo.versions;

import pers.alex.util.upgrade.UpgradeInstanse;
import pers.alex.util.upgrade.UpgradeVersion;

/**
 * 如果前面的版本执行失败了，则后续版本都会跳过执行
 * @author Alex
 * @date 12/2/2020 4:09 PM
 */
@UpgradeVersion(value = "06", desc = "版本6")
public class Version06 implements UpgradeInstanse {

    @Override
    public boolean execute() {



        return true;
    }
}
