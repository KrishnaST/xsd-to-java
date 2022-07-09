package com.fss.aeps.jpa;

import java.util.Objects;

import com.fss.aeps.jaxb.PayConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class ImpsTransactionPayeesId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String txnId;
	private PayConstant txnType;
	private int seqNum;

	public ImpsTransactionPayeesId() {
	}

	public ImpsTransactionPayeesId(String txnId, PayConstant txnType, int seqNum) {
		this.txnId = txnId;
		this.txnType = txnType;
		this.seqNum = seqNum;
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

	public void setTxnType(PayConstant txnType) {
		this.txnType = txnType;
	}

	@Column(name = "SEQ_NUM", nullable = false)
	public int getSeqNum() {
		return this.seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seqNum, txnId, txnType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImpsTransactionPayeesId other = (ImpsTransactionPayeesId) obj;
		return seqNum == other.seqNum && Objects.equals(txnId, other.txnId) && txnType == other.txnType;
	}

}
