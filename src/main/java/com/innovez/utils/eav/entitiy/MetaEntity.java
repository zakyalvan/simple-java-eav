package com.innovez.utils.eav.entitiy;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="innvz_eav_meta_entity")
@SuppressWarnings("serial")
public class MetaEntity implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @NotNull
	@Column(name="entity_name")
	private String entityName;

    @NotNull
	@Column(name="target_type")
	private String targetType;

    @NotEmpty
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "innvz_eav_meta_attributes")
	private Map<String, MetaAttribute> metaAttributes = new HashMap<>();

	@Column(name="created_by")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_timestamp")
	private Date createdTimestamp;
	
	@Column(name="update_by")
	private String updateBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_timestamp")
	private Date updatedTimestamp;
	
	@Version
	@Column(name="record_version")
	private Integer version;
	
	// Default constructor required by hibernate jpa persistence engine.
	MetaEntity() {}
	public MetaEntity(String entityName, String targetType, Map<String, MetaAttribute> metaAttributes) {
		this.entityName = entityName;
		this.targetType = targetType;
		this.metaAttributes.putAll(metaAttributes);
	}

	public Integer getId() {
		return id;
	}
	public String getEntityName() {
		return entityName;
	}
	public String getTargetType() {
		return targetType;
	}

    public Map<String, MetaAttribute> getMetaAttributes() {
        return metaAttributes;
    }

    public String getCreatedBy() {
		return createdBy;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public Integer getVersion() {
		return version;
	}
}
