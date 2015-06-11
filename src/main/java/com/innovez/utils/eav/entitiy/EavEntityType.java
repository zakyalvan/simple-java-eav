package com.innovez.utils.eav.entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="innvz_eav_entity")
@SuppressWarnings("serial")
public class EavEntityType implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="entity_name")
	private String entityName;
	
	@Column(name="target_type")
	private String targetType;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="innvz_eav_meta_attribute")
	private Set<EavMetaAttribute> attributes = new HashSet<>();
	
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
	EavEntityType() {}
	public EavEntityType(String entityName, String targetType, Set<EavMetaAttribute> attributes) {
		this.entityName = entityName;
		this.targetType = targetType;
		this.attributes = attributes;
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
	public Set<EavMetaAttribute> getAttributes() {
		return attributes;
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
