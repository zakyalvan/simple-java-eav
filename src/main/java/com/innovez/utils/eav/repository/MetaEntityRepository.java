package com.innovez.utils.eav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovez.utils.eav.entitiy.MetaEntity;

public interface MetaEntityRepository extends JpaRepository<MetaEntity, Integer> {
	/**
	 * Check whether given eav entity name is already registered.
	 * 
	 * @param name
	 * @return
	 */
	boolean existsByName(String name);
	
	/**
	 * Find one eav entity type determine by given name parameter.
	 * 
	 * @param name
	 * @return
	 */
	MetaEntity findOneByName(String name);
}
