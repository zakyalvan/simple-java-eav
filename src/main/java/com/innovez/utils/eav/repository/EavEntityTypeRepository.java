package com.innovez.utils.eav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovez.utils.eav.entitiy.EavEntityType;

public interface EavEntityTypeRepository extends JpaRepository<EavEntityType, Integer> {
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
	EavEntityType findOneByName(String name);
}
