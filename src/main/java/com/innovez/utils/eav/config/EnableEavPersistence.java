package com.innovez.utils.eav.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Annotation to enable persistence storage using EAV pattern.
 *
 * @author zakyalvan
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(EavPersisteceConfiguration.class)
public @interface EnableEavPersistence {

}
