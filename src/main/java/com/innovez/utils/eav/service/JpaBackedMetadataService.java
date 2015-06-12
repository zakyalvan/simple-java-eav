package com.innovez.utils.eav.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import com.innovez.utils.eav.annotation.EavAttribute;
import com.innovez.utils.eav.annotation.EavEntity;
import com.innovez.utils.eav.annotation.EavId;
import com.innovez.utils.eav.entitiy.MetaEntity;
import com.innovez.utils.eav.entitiy.MetaAttribute;

/**
 * Default implementation of {@link MetadataService} internally backed Jpa persistence engine.
 *
 * @author zakyalvan
 */
@Validated
@Transactional(propagation=Propagation.REQUIRED)
public class JpaBackedMetadataService implements MetadataService {
	public static final Logger LOGGER =
            LoggerFactory.getLogger(JpaBackedMetadataService.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean hasEntityMetadata(String entityName) {
		return false;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createEntityMetadata(Class<?> eavType) {
		LOGGER.debug("Create entity metadata.");

		EavEntity eavEntity = eavType.getAnnotation(EavEntity.class);
		
		String entityName = eavType.getSimpleName();
		String targetType = eavType.getName();
		Map<String, MetaAttribute> metaAttributes = new HashMap<>();
		
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
				metaAttributes.put(field.getName(), metaAttribute);
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

				metaAttributes.put(field.getName(), metaAttribute);
			}
		}
		
		MetaEntity entityType = new MetaEntity(entityName, targetType, metaAttributes);
		entityManager.persist(entityType);
	}

    @Override
    public MetaEntity getEntityMetadata(Class<?> entityType) {
        EavEntity eavEntity = entityType.getClass().getDeclaredAnnotation(EavEntity.class);
        Assert.isTrue(eavEntity != null, "Given type is not valid eav entity");

        String entityName = entityType.getSimpleName();

        String queryString = "SELECT me FROM MetaEntity AS me WHERE me.name = :entityName";
        MetaEntity metaEntity = entityManager.createQuery(queryString, MetaEntity.class)
                .setParameter("entityName", entityName)
                .getSingleResult();
        return metaEntity;
    }
}
