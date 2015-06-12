package com.innovez.utils.eav.entitiy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    public AttributeValue(EntityInstance entityInstance, MetaAttribute metaAttribute) {
        this.entityInstance = entityInstance;
        this.metaAttribute = metaAttribute;
    }

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
