package com.vn.dailycookapp.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;

import com.vn.dailycookapp.utils.json.JsonIgnoreProperty;

@Entity(noClassnameStored = true, value = "Category")
public class Category {
	@Id
	private String	id;
	
	@Property
	@JsonIgnoreProperty
	private String	name;
	
	@Property("default_value")
	private String	value;
	
	@Property(value = "parent_id")
	@Indexed(background = true)
	@JsonIgnoreProperty
	private String	parentId;
	
	@Property
	@JsonIgnoreProperty
	private String	note;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
