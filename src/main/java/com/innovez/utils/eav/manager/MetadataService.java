package com.innovez.utils.eav.manager;

public interface MetadataService {
	boolean hasEntityMetadata(String entityName);
	void createEntityMetadata(Class<?> entityType);
}
