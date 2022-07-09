package com.fss.aeps.jpa;

import java.util.Objects;

import com.fss.aeps.jaxb.PayConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class ImpsTransactionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String txnId;
	private PayConstant txnType;

	public ImpsTransactionId() {
	}

	public ImpsTransactionId(String txnId, PayConstant txnType) {
		this.txnId = txnId;
		this.txnType = txnType;
	}

	@Column(name = "TXN_ID", nullable = false, length = 36)
	public String getTxnId() {
		return this.txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TXN_TYPE", nullable = false, length = 30)
	public PayConstant getTxnType() {
		return this.txnType;
	}

	public void setTxnType(PayConstant payConstant) {
		this.txnType = payConstant;
	}

	@Override
	public int hashCode() {
		return Objects.hash(txnId, txnType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImpsTransactionId other = (ImpsTransactionId) obj;
		return Objects.equals(txnId, other.txnId) && txnType == other.txnType;
	}


	

}
