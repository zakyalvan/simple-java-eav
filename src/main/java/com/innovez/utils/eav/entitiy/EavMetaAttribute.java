package com.innovez.utils.eav.entitiy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Meta attribute of an eav type.
 * 
 * @author zakyalvan
 */
@Embeddable
@SuppressWarnings("serial")
public class EavMetaAttribute implements Serializable {
	@Column(name="name")
	private String name;
	
	@Column(name="field_name")
	private String fieldName;
	
	@Column(name="attribute_type")
	private String type;
	
	@Column(name="is_identifier")
	private boolean identifier;
	
	@Column(name="is_nullable")
	private boolean nullable;
	
	@Column(name="is_unique")
	private boolean unique;

	// Default constructor required by hhibernate's jpa persistence engine.
	public EavMetaAttribute() {}
	public EavMetaAttribute(String name, String fieldName, String type, boolean identifier, boolean nullable, boolean unique) {
		this.name = name;
		this.fieldName = fieldName;
		this.type = type;
		this.identifier = identifier;
		this.nullable = nullable;
		this.unique = unique;
	}

	public String getName() {
		return name;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getType() {
		return type;
	}
	public boolean isIdentifier() {
		return identifier;
	}
	public boolean isNullable() {
		return nullable;
	}
	public boolean isUnique() {
		return unique;
	}
}
