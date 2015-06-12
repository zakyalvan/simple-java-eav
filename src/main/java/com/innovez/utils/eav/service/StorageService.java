package com.innovez.utils.eav.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Contract for eav storage service.
 * 
 * @author zakyalvan
 */
public interface StorageService {
	/**
	 * Save one eav entity.
	 *
	 * @param eavEntity
	 * @return
	 */
	<T> T save(@NotNull @Valid T eavEntity);
	
	/**
	 * Find one eav entity.
	 * 
	 * @param eavType
	 * @param eavId
	 * @return
	 */
	<T> T findOne(@NotNull Object eavId, @NotNull Class<T> eavType);
	<T> List<T> findAll(@NotNull Class<T> eavType);
	<T> T update(@NotNull T eavEntity);
	<T> void remove(@NotNull T eavEntity);
	<T> void remove(@NotNull Object eavId, @NotNull Class<?> eavType);
	<T> void removeAll(@NotNull Class<?> eavType);
}
