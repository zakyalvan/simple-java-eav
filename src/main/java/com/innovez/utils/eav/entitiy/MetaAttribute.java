package com.innovez.utils.eav.entitiy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Meta attribute of an eav type.
 * 
 * @author zakyalvan
 */
@Embeddable
@SuppressWarnings("serial")
public class MetaAttribute implements Serializable {
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
	public MetaAttribute() {}
	public MetaAttribute(String name, String fieldName, String type, boolean identifier, boolean nullable, boolean unique) {
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
