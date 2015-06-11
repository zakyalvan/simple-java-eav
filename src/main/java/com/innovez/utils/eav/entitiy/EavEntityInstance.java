package com.innovez.utils.eav.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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

/**
 * Each record of this type is instance of related {@link EavEntityType}.
 * 
 * @author zakyalvan
 */
@Entity
@Table(name="innvz_eav_instance")
@SuppressWarnings("serial")
public class EavEntityInstance implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entity_id", referencedColumnName="id")
	private EavEntityType entity;
	
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
}
