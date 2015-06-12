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
    /**
     * Type of backed persistence. Currently only support JPA.
     *
     * @return
     */
    PersistenceEngine persistence() default PersistenceEngine.JPA;

    public static enum PersistenceEngine {
        JPA
    }
}
