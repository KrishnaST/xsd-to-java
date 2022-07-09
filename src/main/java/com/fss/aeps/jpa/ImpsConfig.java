package com.fss.aeps.jpa;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "IMPS_CONFIG")
public class ImpsConfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String param;
	private String value;
	private String description;

	public ImpsConfig() {}

	public ImpsConfig(String param) {
		this.param = param;
	}

	public ImpsConfig(String param, String value, String description) {
		this.param       = param;
		this.value       = value;
		this.description = description;
	}

	@Id
	@Column(name = "PARAM", unique = true, nullable = false, length = 50)
	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Column(name = "VALUE", length = 1024)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "DESCRIPTION", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[param=" + param + ", value=" + value + ", description=" + description + "]\r\n";
	}
	
	

}
