package com.innovez.utils.eav.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marking any fields in entity as eav attribute.
 * 
 * @author zakyalvan
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EavAttribute {
	/**
	 * Name or alias for attribute, default to empty string,
	 * which means, name of attribute will be used.
	 * 
	 * @return
	 */
	String name() default "";
	
	/**
	 * Attribute is unique, default is false.
	 * 
	 * @return
	 */
	boolean unique() default false;
	
	/**
	 * Attribute is null-able, default is true.
	 * 
	 * @return
	 */
	boolean nullable() default true;
}
