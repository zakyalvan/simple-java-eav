package com.innovez.utils.eav.service;

import com.innovez.utils.eav.annotation.EavEntity;
import com.innovez.utils.eav.entitiy.AttributeValue;
import com.innovez.utils.eav.entitiy.EntityInstance;
import com.innovez.utils.eav.entitiy.MetaAttribute;
import com.innovez.utils.eav.entitiy.MetaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Default implementation of {@link StorageService} interface,
 * this object type backed by jpa persistence engine.
 * 
 * @author zakyalvan
 */
@Service
@Validated
@Transactional(readOnly=true)
public class JpaBackedStorageService implements StorageService {
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(JpaBackedStorageService.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private MetadataService metadataService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> T save(T eavEntity) {
		Assert.isTrue(isValidEavType(eavEntity.getClass()), 
				String.format("Given type (%s) is not valid eav entity type", eavEntity.getClass().getTypeName()));

        MetaEntity metaEntity = metadataService.getEntityMetadata(eavEntity.getClass());
        Map<String, MetaAttribute> metaAttributes = metaEntity.getMetaAttributes();

        EntityInstance entityInstance = new EntityInstance();
        entityInstance.setMetaEntity(metaEntity);
        entityManager.persist(entityInstance);

        List<AttributeValue> attributeValues = new ArrayList<>();
        Field[] fields = eavEntity.getClass().getFields();
        for(Field field : fields) {
            if(metaAttributes.keySet().contains(field.getName())) {
                try {
                    boolean accessChanged = false;
                    boolean defaultAccessible = true;

                    if (!field.isAccessible()) {
                        field.setAccessible(true);

                        defaultAccessible = false;
                        accessChanged = true;
                    }

                    Object value = field.get(eavEntity);
                    AttributeValue attributeValue = new AttributeValue(entityInstance, metaAttributes.get(field.getName()));
                    attributeValues.add(attributeValue);

                    if(accessChanged) {
                        field.setAccessible(defaultAccessible);
                    }
                }
                catch (IllegalAccessException iae) {
                    LOGGER.error("An illegal access exception thrown, look at stack trace", iae);
                }
                catch (Exception e) {

                }
            }
        }

		return eavEntity;
	}
	
	@Override
	public <T> T findOne(Object id, Class<T> eavType) {
		Assert.isTrue(isValidEavType(eavType), 
				String.format("Given type (%s) is not valid eav entity type", eavType.getTypeName()));
		
		EavEntity eavEntity = eavType.getAnnotation(EavEntity.class);
		String entityName = eavEntity.getClass().getSimpleName();
		
		if(!metadataService.hasEntityMetadata(entityName)) {
			LOGGER.debug("Create metadata record of given eav entity type, its also means no record yet on database for that type");
			metadataService.createEntityMetadata(eavType);
			return null;
		}
		
		return null;
	}
	
	@Override
	public <T> List<T> findAll(Class<T> eavType) {
		return null;
	}

	@Override
	public <T> T update(T eavEntity) {
		return null;
	}

	@Override
	public <T> void remove(T eavEntity) {
		
	}

	@Override
	public <T> void remove(Object eavId, Class<?> eavType) {
		
	}

	@Override
	public <T> void removeAll(Class<?> eavType) {
		
	}

	/**
	 * Check whether given class parameter has eav metadata.
	 * Simply check whether given marked with {@link EavEntity} annotation.
	 * 
	 * @param eavType
	 * @return
	 */
	private boolean isValidEavType(Class<?> eavType) {
		List<Annotation> annotations = Arrays.asList(eavType.getAnnotations());
		if(annotations.contains(EavEntity.class)) {
			return true;
		}
		return false;
	}
}
