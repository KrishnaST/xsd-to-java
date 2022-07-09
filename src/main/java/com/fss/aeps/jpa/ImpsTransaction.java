package com.fss.aeps.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fss.aeps.jaxb.AddressType;
import com.fss.aeps.jaxb.PayerConstant;
import com.fss.aeps.jaxb.ProdType;
import com.fss.aeps.jaxb.ResultType;
import com.fss.aeps.jaxb.TxnSubType;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "IMPS_TRANSACTION")
public class ImpsTransaction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private ImpsTransactionId id;
	private TxnSubType txnSubType;
	private String msgId;
	private Date msgTs;
	private ProdType prodType;
	private String orgId;
	private String msgVer;
	private String custRef;
	private String refId;
	private String refCategory;
	private String initiationMode;
	private String note;
	private String purpose;
	private Date txnTs;
	private String payerAddr;
	private String payerCode;
	private String payerName;
	private int payerSeqNum;
	private PayerConstant payerType;
	private boolean verifiedAddr;
	private String deviceDetails;
	private AddressType payerAcAddrType;
	private String payerAcDetails;
	private String payerAmountCurrency;
	private BigDecimal payerAmount;
	private Date creationTime;
	private Date updationTime;
	private String respMsgId;
	private Date respMsgTs;
	private ResultType respResult;
	private String respErrorCode;
	private String respCode;
	private String respApprovalNo;

	private List<ImpsTransactionPayees> payees = new ArrayList<ImpsTransactionPayees>(0);

	public ImpsTransaction() {
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "txnId", column = @Column(name = "TXN_ID", nullable = false, length = 36)),
			@AttributeOverride(name = "txnType", column = @Column(name = "TXN_TYPE", nullable = false, length = 30)) })
	public ImpsTransactionId getId() {
		return this.id;
	}

	public void setId(ImpsTransactionId id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TXN_SUB_TYPE", length = 30)
	public TxnSubType getTxnSubType() {
		return this.txnSubType;
	}

	public void setTxnSubType(TxnSubType txnSubType) {
		this.txnSubType = txnSubType;
	}

	@Column(name = "MSG_ID", nullable = false, length = 36)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MSG_TS", length = 23)
	public Date getMsgTs() {
		return this.msgTs;
	}

	public void setMsgTs(Date msgTs) {
		this.msgTs = msgTs;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PROD_TYPE", length = 10)
	public ProdType getProdType() {
		return this.prodType;
	}

	public void setProdType(ProdType prodType) {
		this.prodType = prodType;
	}

	@Column(name = "ORG_ID", length = 10)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "MSG_VER", length = 10)
	public String getMsgVer() {
		return this.msgVer;
	}

	public void setMsgVer(String msgVer) {
		this.msgVer = msgVer;
	}

	@Column(name = "CUST_REF", length = 36)
	public String getCustRef() {
		return this.custRef;
	}

	public void setCustRef(String custRef) {
		this.custRef = custRef;
	}

	@Column(name = "REF_ID", length = 36)
	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	@Column(name = "REF_CATEGORY", length = 2)
	public String getRefCategory() {
		return this.refCategory;
	}

	public void setRefCategory(String refCategory) {
		this.refCategory = refCategory;
	}

	@Column(name = "INITIATION_MODE", length = 2)
	public String getInitiationMode() {
		return this.initiationMode;
	}

	public void setInitiationMode(String initiationMode) {
		this.initiationMode = initiationMode;
	}

	@Column(name = "NOTE", length = 256)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "PURPOSE", length = 2)
	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TXN_TS", length = 23)
	public Date getTxnTs() {
		return this.txnTs;
	}

	public void setTxnTs(Date txnTs) {
		this.txnTs = txnTs;
	}

	@Column(name = "PAYER_ADDR", length = 100)
	public String getPayerAddr() {
		return this.payerAddr;
	}

	public void setPayerAddr(String payerAddr) {
		this.payerAddr = payerAddr;
	}

	@Column(name = "PAYER_CODE", length = 400)
	public String getPayerCode() {
		return this.payerCode;
	}

	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}

	@Column(name = "PAYER_NAME", length = 100)
	public String getPayerName() {
		return this.payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	@Column(name = "PAYER_SEQ_NUM", nullable = false)
	public int getPayerSeqNum() {
		return this.payerSeqNum;
	}

	public void setPayerSeqNum(int payerSeqNum) {
		this.payerSeqNum = payerSeqNum;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PAYER_TYPE", length = 30)
	public PayerConstant getPayerType() {
		return this.payerType;
	}

	public void setPayerType(PayerConstant payerType) {
		this.payerType = payerType;
	}

	@Column(name = "VERIFIED_ADDR", nullable = false)
	public boolean isVerifiedAddr() {
		return this.verifiedAddr;
	}

	public void setVerifiedAddr(boolean verifiedAddr) {
		this.verifiedAddr = verifiedAddr;
	}

	@Column(name = "DEVICE_DETAILS", length = 2048)
	public String getDeviceDetails() {
		return this.deviceDetails;
	}

	public void setDeviceDetails(String deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PAYER_AC_ADDR_TYPE", nullable = false, length = 30)
	public AddressType getPayerAcAddrType() {
		return this.payerAcAddrType;
	}

	public void setPayerAcAddrType(AddressType payerAcAddrType) {
		this.payerAcAddrType = payerAcAddrType;
	}

	@Column(name = "PAYER_AC_DETAILS", length = 512)
	public String getPayerAcDetails() {
		return this.payerAcDetails;
	}

	public void setPayerAcDetails(String payerAcDetails) {
		this.payerAcDetails = payerAcDetails;
	}

	@Column(name = "PAYER_AMOUNT_CURRENCY", length = 3)
	public String getPayerAmountCurrency() {
		return this.payerAmountCurrency;
	}

	public void setPayerAmountCurrency(String payerAmountCurrency) {
		this.payerAmountCurrency = payerAmountCurrency;
	}

	@Column(name = "PAYER_AMOUNT", nullable = false, precision = 12)
	public BigDecimal getPayerAmount() {
		return this.payerAmount;
	}

	public void setPayerAmount(BigDecimal payerAmount) {
		this.payerAmount = payerAmount;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_TIME", nullable = true, length = 23)
	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATION_TIME", nullable = true, length = 23)
	public Date getUpdationTime() {
		return updationTime;
	}

	public void setUpdationTime(Date updationTime) {
		this.updationTime = updationTime;
	}

	@Column(name = "RESP_MSG_ID", length = 36)
	public String getRespMsgId() {
		return this.respMsgId;
	}

	public void setRespMsgId(String respMsgId) {
		this.respMsgId = respMsgId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESP_MSG_TS", length = 23)
	public Date getRespMsgTs() {
		return this.respMsgTs;
	}

	public void setRespMsgTs(Date respMsgTs) {
		this.respMsgTs = respMsgTs;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "RESP_RESULT", length = 20)
	public ResultType getRespResult() {
		return this.respResult;
	}

	public void setRespResult(ResultType resultType) {
		this.respResult = resultType;
	}

	@Column(name = "RESP_ERROR_CODE", length = 3)
	public String getRespErrorCode() {
		return this.respErrorCode;
	}

	public void setRespErrorCode(String respErrorCode) {
		this.respErrorCode = respErrorCode;
	}

	@Column(name = "RESP_CODE", length = 3)
	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Column(name = "RESP_APPROVAL_NO", length = 6)
	public String getRespApprovalNo() {
		return this.respApprovalNo;
	}

	public void setRespApprovalNo(String respApprovalNo) {
		this.respApprovalNo = respApprovalNo;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "impsTransaction", cascade = CascadeType.ALL)
	public List<ImpsTransactionPayees> getPayees() {
		return this.payees;
	}

	public void setPayees(List<ImpsTransactionPayees> payees) {
		this.payees = payees;
	}

}
