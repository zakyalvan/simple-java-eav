package com.innovez.utils.eav.config;

import com.innovez.utils.eav.service.JpaBackedMetadataService;
import com.innovez.utils.eav.service.JpaBackedStorageService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * Default configuration of eav persistence.
 *
 * @author zakyalvan
 */
@Configuration
@PropertySource("classpath:eav.properties")
public class EavPersisteceConfiguration implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(EnableEavPersistence.class.getName());

        EnableEavPersistence.PersistenceEngine persistenceEngine =
                (EnableEavPersistence.PersistenceEngine) annotationAttributes.get("persistence");

        if(persistenceEngine == EnableEavPersistence.PersistenceEngine.JPA) {
            BeanDefinition metadataServiceBean = BeanDefinitionBuilder
                    .rootBeanDefinition(JpaBackedMetadataService.class)
                    .setAutowireMode(ScannedGenericBeanDefinition.AUTOWIRE_BY_TYPE)
                    .getBeanDefinition();
            registry.registerBeanDefinition("metadataService", metadataServiceBean);

            BeanDefinition storageServiceBean = BeanDefinitionBuilder
                    .rootBeanDefinition(JpaBackedStorageService.class)
                    .setAutowireMode(ScannedGenericBeanDefinition.AUTOWIRE_BY_TYPE)
                    .getBeanDefinition();
            registry.registerBeanDefinition("storageService", storageServiceBean);
        }
    }
}
