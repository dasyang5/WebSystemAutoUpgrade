package pers.alex.demo.versions;

import pers.alex.util.upgrade.UpgradeInstanse;
import pers.alex.util.upgrade.UpgradeVersion;

/**
 * 如果版本小于等于当前版本则会跳过执行
 *
 * @author Alex
 * @date 12/2/2020 4:09 PM
 */
@UpgradeVersion(value = "01", desc = "版本1")
public class Version01 implements UpgradeInstanse {

    @Override
    public boolean execute() {



        return true;
    }
}
