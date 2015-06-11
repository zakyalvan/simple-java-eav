package com.innovez.utils.eav.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marked any type so that can be persisted as eav entity.
 * 
 * @author zakyalvan
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EavEntity {
	/**
	 * Name of entity, if not given, fully qualified class name 
	 * of annotated type will be used as entity name.
	 * 
	 * @return
	 */
	String name() default "";
}
