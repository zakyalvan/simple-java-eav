package com.innovez.utils.eav.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="innvz_eav_attribute_value")
@SuppressWarnings("serial")
public class AttributeValue implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entity_instance_id", referencedColumnName="id")
	private EntityInstance entityInstance;
	
	@Embedded
	private MetaAttribute metaAttribute;
	
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

	public Long getId() {
		return id;
	}

	public EntityInstance getEntityInstance() {
		return entityInstance;
	}
	
	public MetaAttribute getMetaAttribute() {
		return metaAttribute;
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
}
