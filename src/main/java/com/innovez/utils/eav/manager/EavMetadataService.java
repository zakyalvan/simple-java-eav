package com.innovez.utils.eav.manager;

public interface EavMetadataService {
	boolean hasEntityMetadata(String entityName);
	void createEntityMetadata(Class<?> entityType);
}
