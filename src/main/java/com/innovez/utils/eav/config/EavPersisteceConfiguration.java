package com.innovez.utils.eav.config;

import com.innovez.utils.eav.service.JpaBackedMetadataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zakyalvan
 */
@Configuration
public class EavPersisteceConfiguration {
    @Bean
    public JpaBackedMetadataService metadataService() {
        return new JpaBackedMetadataService();
    }
}
