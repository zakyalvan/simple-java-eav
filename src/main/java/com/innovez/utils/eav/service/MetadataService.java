package com.innovez.utils.eav.service;

import com.innovez.utils.eav.entitiy.MetaEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author zakyalvan
 */
public interface MetadataService {
	/**
	 * Ask whether given type parameter already have metadata.
     *
	 * @param entityName
	 * @return
	 */
	boolean hasEntityMetadata(@NotBlank String entityName);

    /**
     * Create entity metadata and save them into persistence storage.
     *
     * @param entityType
     */
	void createEntityMetadata(@NotNull Class<?> entityType);

    /**
     * Retrieve entity metadata ({@link MetaEntity}).
     *
     * @param entityType
     * @return
     */
    MetaEntity getEntityMetadata(@NotNull Class<?> entityType);
}
