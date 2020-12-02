package pers.alex.util.upgrade;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alex
 * @date 12/1/2020 11:11 AM
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UpgradeVersion {

    /**
     * Version
     * @return
     */
    String value() default "";

    /**
     * Description
     * @return
     */
    String desc() default  "";

    /**
     * 是否启用
     * @return
     */
    boolean disable() default false;

}
