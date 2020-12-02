package pers.alex.demo.versions;

import pers.alex.util.upgrade.UpgradeInstanse;
import pers.alex.util.upgrade.UpgradeVersion;

/**
 * 未实现接口会报错
 * @author Alex
 * @date 12/2/2020 4:09 PM
 */
@UpgradeVersion(value = "03", desc = "版本3")
public class Version03 {

    public boolean execute() {



        return true;
    }
}
