package com.innovez.utils.eav.manager;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.innovez.utils.eav.annotation.EavAttribute;
import com.innovez.utils.eav.annotation.EavEntity;
import com.innovez.utils.eav.annotation.EavId;
import com.innovez.utils.eav.entitiy.MetaEntity;
import com.innovez.utils.eav.entitiy.MetaAttribute;

/**
 * 
 * 
 * @author zakyalvan
 */
@Service
@Validated
@Transactional(propagation=Propagation.REQUIRED)
public class JpaBackedMetadataService implements MetadataService {
	public static final Logger LOGGER = LoggerFactory.getLogger(JpaBackedMetadataService.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean hasEntityMetadata(String entityName) {
		return false;
	}
	
	/**
	 * Parse and create (save to database) eav entity type metadata.
	 * 
	 * @param type
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createEntityMetadata(Class<?> eavType) {
		EavEntity eavEntity = eavType.getAnnotation(EavEntity.class);
		
		String entityName = eavEntity.name() != null ? eavEntity.name() : eavType.getSimpleName();
		String targetType = eavType.getName();
		Set<MetaAttribute> metaAttributes = new HashSet<>();
		
		Field[] fields = eavType.getDeclaredFields();
		for(Field field : fields) {
			EavId eavId = field.getDeclaredAnnotation(EavId.class);
			if(eavId != null) {
				MetaAttribute metaAttribute = new MetaAttribute(
						field.getName(), 
						field.getName(), 
						field.getType().getName(), 
						true, 
						false, 
						true);
				metaAttributes.add(metaAttribute);
			}
			
			EavAttribute eavAttribute = field.getAnnotation(EavAttribute.class);
			if(eavAttribute != null) {
				MetaAttribute metaAttribute = new MetaAttribute(
						!eavAttribute.name().isEmpty() ? eavAttribute.name() : field.getName(),
						field.getName(), 
						field.getType().getName(), 
						false, 
						eavAttribute.nullable(), 
						eavAttribute.unique());
				metaAttributes.add(metaAttribute);
				
				metaAttributes.add(metaAttribute);
			}
		}
		
		MetaEntity entityType = new MetaEntity(entityName, targetType, metaAttributes);
		entityManager.persist(entityType);
	}
}
