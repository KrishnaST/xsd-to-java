//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payTrans complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="payTrans">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="RiskScores" type="{http://npci.org/upi/schema/}riskScoresType" minOccurs="0"/>
 *         <element name="Rules" type="{http://npci.org/upi/schema/}rulesType" minOccurs="0"/>
 *         <element name="QR" type="{http://npci.org/upi/schema/}qrType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="note" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="successRefId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="successTs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="refId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="refUrl" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="ts" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="type" type="{http://npci.org/upi/schema/}payConstant" />
 *       <attribute name="orgMsgId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgTxnId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgRespCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgApprovalNum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="custRef" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgTxnDate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgRrn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="category" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="umn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="initiationMode" type="{http://npci.org/upi/schema/}initiationModeCode" />
 *       <attribute name="subType" type="{http://npci.org/upi/schema/}txnSubType" />
 *       <attribute name="initiatedBy" type="{http://npci.org/upi/schema/}initiatedByType" />
 *       <attribute name="pspOrgId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="purpose" type="{http://npci.org/upi/schema/}txnPurpose" />
 *       <attribute name="lastUpdTs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="crtnTs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="refCategory" type="{http://npci.org/upi/schema/}txnRefCategory" />
 *       <attribute name="action" type="{http://npci.org/upi/schema/}actionConstant" />
 *       <attribute name="valStart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="valEnd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="dealtType" type="{http://npci.org/upi/schema/}dealtTypeType" />
 *       <attribute name="dealtSide" type="{http://npci.org/upi/schema/}dealtSideType" />
 *       <attribute name="umnExecTs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgRespMsg" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="depositId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="orgTxnAmt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="op" type="{http://npci.org/upi/schema/}opType" />
 *       <attribute name="addr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payTrans", propOrder = {
    "riskScores",
    "rules",
    "qr"
})
public class PayTrans {

    @XmlElement(name = "RiskScores")
    protected RiskScoresType riskScores;
    @XmlElement(name = "Rules")
    protected RulesType rules;
    @XmlElement(name = "QR")
    protected QrType qr;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "note")
    protected String note;
    @XmlAttribute(name = "successRefId")
    protected String successRefId;
    @XmlAttribute(name = "successTs")
    protected String successTs;
    @XmlAttribute(name = "refId")
    protected String refId;
    @XmlAttribute(name = "refUrl")
    protected String refUrl;
    @XmlAttribute(name = "ts")
    protected String ts;
    @XmlAttribute(name = "type")
    protected PayConstant type;
    @XmlAttribute(name = "orgMsgId")
    protected String orgMsgId;
    @XmlAttribute(name = "orgTxnId")
    protected String orgTxnId;
    @XmlAttribute(name = "orgRespCode")
    protected String orgRespCode;
    @XmlAttribute(name = "orgApprovalNum")
    protected String orgApprovalNum;
    @XmlAttribute(name = "custRef")
    protected String custRef;
    @XmlAttribute(name = "orgTxnDate")
    protected String orgTxnDate;
    @XmlAttribute(name = "orgRrn")
    protected String orgRrn;
    @XmlAttribute(name = "category")
    protected String category;
    @XmlAttribute(name = "umn")
    protected String umn;
    @XmlAttribute(name = "initiationMode")
    protected String initiationMode;
    @XmlAttribute(name = "subType")
    protected TxnSubType subType;
    @XmlAttribute(name = "initiatedBy")
    protected InitiatedByType initiatedBy;
    @XmlAttribute(name = "pspOrgId")
    protected String pspOrgId;
    @XmlAttribute(name = "purpose")
    protected String purpose;
    @XmlAttribute(name = "lastUpdTs")
    protected String lastUpdTs;
    @XmlAttribute(name = "crtnTs")
    protected String crtnTs;
    @XmlAttribute(name = "refCategory")
    protected String refCategory;
    @XmlAttribute(name = "action")
    protected ActionConstant action;
    @XmlAttribute(name = "valStart")
    protected String valStart;
    @XmlAttribute(name = "valEnd")
    protected String valEnd;
    @XmlAttribute(name = "dealtType")
    protected DealtTypeType dealtType;
    @XmlAttribute(name = "dealtSide")
    protected DealtSideType dealtSide;
    @XmlAttribute(name = "umnExecTs")
    protected String umnExecTs;
    @XmlAttribute(name = "orgRespMsg")
    protected String orgRespMsg;
    @XmlAttribute(name = "depositId")
    protected String depositId;
    @XmlAttribute(name = "orgTxnAmt")
    protected String orgTxnAmt;
    @XmlAttribute(name = "op")
    protected OpType op;
    @XmlAttribute(name = "addr")
    protected String addr;

    /**
     * Gets the value of the riskScores property.
     * 
     * @return
     *     possible object is
     *     {@link RiskScoresType }
     *     
     */
    public RiskScoresType getRiskScores() {
        return riskScores;
    }

    /**
     * Sets the value of the riskScores property.
     * 
     * @param value
     *     allowed object is
     *     {@link RiskScoresType }
     *     
     */
    public void setRiskScores(RiskScoresType value) {
        this.riskScores = value;
    }

    /**
     * Gets the value of the rules property.
     * 
     * @return
     *     possible object is
     *     {@link RulesType }
     *     
     */
    public RulesType getRules() {
        return rules;
    }

    /**
     * Sets the value of the rules property.
     * 
     * @param value
     *     allowed object is
     *     {@link RulesType }
     *     
     */
    public void setRules(RulesType value) {
        this.rules = value;
    }

    /**
     * Gets the value of the qr property.
     * 
     * @return
     *     possible object is
     *     {@link QrType }
     *     
     */
    public QrType getQR() {
        return qr;
    }

    /**
     * Sets the value of the qr property.
     * 
     * @param value
     *     allowed object is
     *     {@link QrType }
     *     
     */
    public void setQR(QrType value) {
        this.qr = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the successRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessRefId() {
        return successRefId;
    }

    /**
     * Sets the value of the successRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessRefId(String value) {
        this.successRefId = value;
    }

    /**
     * Gets the value of the successTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessTs() {
        return successTs;
    }

    /**
     * Sets the value of the successTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessTs(String value) {
        this.successTs = value;
    }

    /**
     * Gets the value of the refId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Sets the value of the refId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefId(String value) {
        this.refId = value;
    }

    /**
     * Gets the value of the refUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefUrl() {
        return refUrl;
    }

    /**
     * Sets the value of the refUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefUrl(String value) {
        this.refUrl = value;
    }

    /**
     * Gets the value of the ts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTs() {
        return ts;
    }

    /**
     * Sets the value of the ts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTs(String value) {
        this.ts = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PayConstant }
     *     
     */
    public PayConstant getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayConstant }
     *     
     */
    public void setType(PayConstant value) {
        this.type = value;
    }

    /**
     * Gets the value of the orgMsgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgMsgId() {
        return orgMsgId;
    }

    /**
     * Sets the value of the orgMsgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgMsgId(String value) {
        this.orgMsgId = value;
    }

    /**
     * Gets the value of the orgTxnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgTxnId() {
        return orgTxnId;
    }

    /**
     * Sets the value of the orgTxnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgTxnId(String value) {
        this.orgTxnId = value;
    }

    /**
     * Gets the value of the orgRespCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgRespCode() {
        return orgRespCode;
    }

    /**
     * Sets the value of the orgRespCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgRespCode(String value) {
        this.orgRespCode = value;
    }

    /**
     * Gets the value of the orgApprovalNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgApprovalNum() {
        return orgApprovalNum;
    }

    /**
     * Sets the value of the orgApprovalNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgApprovalNum(String value) {
        this.orgApprovalNum = value;
    }

    /**
     * Gets the value of the custRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustRef() {
        return custRef;
    }

    /**
     * Sets the value of the custRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustRef(String value) {
        this.custRef = value;
    }

    /**
     * Gets the value of the orgTxnDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgTxnDate() {
        return orgTxnDate;
    }

    /**
     * Sets the value of the orgTxnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgTxnDate(String value) {
        this.orgTxnDate = value;
    }

    /**
     * Gets the value of the orgRrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgRrn() {
        return orgRrn;
    }

    /**
     * Sets the value of the orgRrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgRrn(String value) {
        this.orgRrn = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the umn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUmn() {
        return umn;
    }

    /**
     * Sets the value of the umn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUmn(String value) {
        this.umn = value;
    }

    /**
     * Gets the value of the initiationMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitiationMode() {
        return initiationMode;
    }

    /**
     * Sets the value of the initiationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitiationMode(String value) {
        this.initiationMode = value;
    }

    /**
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link TxnSubType }
     *     
     */
    public TxnSubType getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TxnSubType }
     *     
     */
    public void setSubType(TxnSubType value) {
        this.subType = value;
    }

    /**
     * Gets the value of the initiatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link InitiatedByType }
     *     
     */
    public InitiatedByType getInitiatedBy() {
        return initiatedBy;
    }

    /**
     * Sets the value of the initiatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitiatedByType }
     *     
     */
    public void setInitiatedBy(InitiatedByType value) {
        this.initiatedBy = value;
    }

    /**
     * Gets the value of the pspOrgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPspOrgId() {
        return pspOrgId;
    }

    /**
     * Sets the value of the pspOrgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPspOrgId(String value) {
        this.pspOrgId = value;
    }

    /**
     * Gets the value of the purpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Sets the value of the purpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurpose(String value) {
        this.purpose = value;
    }

    /**
     * Gets the value of the lastUpdTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdTs() {
        return lastUpdTs;
    }

    /**
     * Sets the value of the lastUpdTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdTs(String value) {
        this.lastUpdTs = value;
    }

    /**
     * Gets the value of the crtnTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrtnTs() {
        return crtnTs;
    }

    /**
     * Sets the value of the crtnTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrtnTs(String value) {
        this.crtnTs = value;
    }

    /**
     * Gets the value of the refCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefCategory() {
        return refCategory;
    }

    /**
     * Sets the value of the refCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefCategory(String value) {
        this.refCategory = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link ActionConstant }
     *     
     */
    public ActionConstant getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionConstant }
     *     
     */
    public void setAction(ActionConstant value) {
        this.action = value;
    }

    /**
     * Gets the value of the valStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValStart() {
        return valStart;
    }

    /**
     * Sets the value of the valStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValStart(String value) {
        this.valStart = value;
    }

    /**
     * Gets the value of the valEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValEnd() {
        return valEnd;
    }

    /**
     * Sets the value of the valEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValEnd(String value) {
        this.valEnd = value;
    }

    /**
     * Gets the value of the dealtType property.
     * 
     * @return
     *     possible object is
     *     {@link DealtTypeType }
     *     
     */
    public DealtTypeType getDealtType() {
        return dealtType;
    }

    /**
     * Sets the value of the dealtType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DealtTypeType }
     *     
     */
    public void setDealtType(DealtTypeType value) {
        this.dealtType = value;
    }

    /**
     * Gets the value of the dealtSide property.
     * 
     * @return
     *     possible object is
     *     {@link DealtSideType }
     *     
     */
    public DealtSideType getDealtSide() {
        return dealtSide;
    }

    /**
     * Sets the value of the dealtSide property.
     * 
     * @param value
     *     allowed object is
     *     {@link DealtSideType }
     *     
     */
    public void setDealtSide(DealtSideType value) {
        this.dealtSide = value;
    }

    /**
     * Gets the value of the umnExecTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUmnExecTs() {
        return umnExecTs;
    }

    /**
     * Sets the value of the umnExecTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUmnExecTs(String value) {
        this.umnExecTs = value;
    }

    /**
     * Gets the value of the orgRespMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgRespMsg() {
        return orgRespMsg;
    }

    /**
     * Sets the value of the orgRespMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgRespMsg(String value) {
        this.orgRespMsg = value;
    }

    /**
     * Gets the value of the depositId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositId() {
        return depositId;
    }

    /**
     * Sets the value of the depositId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositId(String value) {
        this.depositId = value;
    }

    /**
     * Gets the value of the orgTxnAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgTxnAmt() {
        return orgTxnAmt;
    }

    /**
     * Sets the value of the orgTxnAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgTxnAmt(String value) {
        this.orgTxnAmt = value;
    }

    /**
     * Gets the value of the op property.
     * 
     * @return
     *     possible object is
     *     {@link OpType }
     *     
     */
    public OpType getOp() {
        return op;
    }

    /**
     * Sets the value of the op property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpType }
     *     
     */
    public void setOp(OpType value) {
        this.op = value;
    }

    /**
     * Gets the value of the addr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr() {
        return addr;
    }

    /**
     * Sets the value of the addr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr(String value) {
        this.addr = value;
    }

}
