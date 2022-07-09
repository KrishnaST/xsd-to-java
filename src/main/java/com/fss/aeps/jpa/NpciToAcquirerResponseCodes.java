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
@Table(name = "NPCI_TO_ACQUIRER_RESPONSE_CODE")
public class NpciToAcquirerResponseCodes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String npciCode;
	private String acquirerCode;
	private String description;

	public NpciToAcquirerResponseCodes() {
	}

	@Id
	@Column(name = "NPCI_CODE", unique = true, nullable = false, length = 3)
	public String getNpciCode() {
		return npciCode;
	}

	public void setNpciCode(String npciCode) {
		this.npciCode = npciCode;
	}
	
	@Column(name = "ACQUIRER_CODE", length = 3)
	public String getAcquirerCode() {
		return acquirerCode;
	}

	public void setAcquirerCode(String acquirerCode) {
		this.acquirerCode = acquirerCode;
	}

	@Column(name = "DESCRIPTION", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
