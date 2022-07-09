package com.fss.aeps.jpa;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "IMPS_TRANSACTION_PAYEES")
public class ImpsTransactionPayees implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private ImpsTransactionPayeesId id;
	private ImpsTransaction impsTransaction;
	private String code;
	private String type;
	private String acnum;
	private String ifsc;
	private String actype;
	private BigDecimal amount;

	public ImpsTransactionPayees() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "txnId", column = @Column(name = "TXN_ID", nullable = false, length = 36)),
			@AttributeOverride(name = "txnType", column = @Column(name = "TXN_TYPE", nullable = false, length = 30)),
			@AttributeOverride(name = "seqNum", column = @Column(name = "SEQ_NUM", nullable = false)) })
	public ImpsTransactionPayeesId getId() {
		return this.id;
	}

	public void setId(ImpsTransactionPayeesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumns({
			@JoinColumn(name = "TXN_ID", referencedColumnName = "TXN_ID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "TXN_TYPE", referencedColumnName = "TXN_TYPE", nullable = false, insertable = false, updatable = false) })
	public ImpsTransaction getImpsTransaction() {
		return this.impsTransaction;
	}

	public void setImpsTransaction(ImpsTransaction impsTransaction) {
		this.impsTransaction = impsTransaction;
	}

	@Column(name = "CODE", length = 400)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "TYPE", length = 30)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ACNUM", length = 30)
	public String getAcnum() {
		return this.acnum;
	}

	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}

	@Column(name = "IFSC", length = 11)
	public String getIfsc() {
		return this.ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	@Column(name = "ACTYPE", length = 30)
	public String getActype() {
		return this.actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 10)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
