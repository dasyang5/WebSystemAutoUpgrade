package pers.alex.util.upgrade;

/**
 * @author Alex
 * @date 12/2/2020 1:58 PM
 */
public class ClassWrapper {

    private Class clazz;

    private String value;

    private String desc;

    private boolean disable;

    public ClassWrapper(Class clazz, String value, String desc, boolean disable) {
        this.clazz = clazz;
        this.value = value;
        this.desc = desc;
        this.disable = disable;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
