package com.innovez.utils.eav.manager;

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
	 * @param eavType
	 * @param eavEntity
	 * @return
	 */
	<T> T save(T eavEntity);
	
	/**
	 * Find one eav entity.
	 * 
	 * @param eavType
	 * @param id
	 * @return
	 */
	<T> T findOne(Object eavId, Class<T> eavType);
	<T> List<T> findAll(Class<T> eavType);
	<T> T update(T eavEntity);
	<T> void remove(T eavEntity);
	<T> void remove(Object eavId, Class<?> eavType);
	<T> void removeAll(Class<?> eavType);
}
