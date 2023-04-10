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
 * <p>Java class for mandateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="mandateType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Validity" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <attribute name="start" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="end" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="Amount" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="rule" use="required" type="{http://npci.org/upi/schema/}amtRuleType" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="Recurrence" type="{http://npci.org/upi/schema/}recurrenceType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="txnId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="umn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="ts" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="revokeable" type="{http://npci.org/upi/schema/}revokeableType" />
 *       <attribute name="shareToPayee" type="{http://npci.org/upi/schema/}shareToPayee" />
 *       <attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="blockFund" type="{http://npci.org/upi/schema/}blockFund" />
 *       <attribute name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mandateType", propOrder = {
    "validity",
    "amount",
    "recurrence"
})
public class MandateType {

    @XmlElement(name = "Validity")
    protected MandateType.Validity validity;
    @XmlElement(name = "Amount")
    protected MandateType.Amount amount;
    @XmlElement(name = "Recurrence")
    protected RecurrenceType recurrence;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "txnId")
    protected String txnId;
    @XmlAttribute(name = "umn")
    protected String umn;
    @XmlAttribute(name = "ts")
    protected String ts;
    @XmlAttribute(name = "revokeable")
    protected RevokeableType revokeable;
    @XmlAttribute(name = "shareToPayee")
    protected ShareToPayee shareToPayee;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "blockFund")
    protected BlockFund blockFund;
    @XmlAttribute(name = "uuid")
    protected String uuid;

    /**
     * Gets the value of the validity property.
     * 
     * @return
     *     possible object is
     *     {@link MandateType.Validity }
     *     
     */
    public MandateType.Validity getValidity() {
        return validity;
    }

    /**
     * Sets the value of the validity property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandateType.Validity }
     *     
     */
    public void setValidity(MandateType.Validity value) {
        this.validity = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link MandateType.Amount }
     *     
     */
    public MandateType.Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MandateType.Amount }
     *     
     */
    public void setAmount(MandateType.Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the recurrence property.
     * 
     * @return
     *     possible object is
     *     {@link RecurrenceType }
     *     
     */
    public RecurrenceType getRecurrence() {
        return recurrence;
    }

    /**
     * Sets the value of the recurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecurrenceType }
     *     
     */
    public void setRecurrence(RecurrenceType value) {
        this.recurrence = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the txnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnId() {
        return txnId;
    }

    /**
     * Sets the value of the txnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnId(String value) {
        this.txnId = value;
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
     * Gets the value of the revokeable property.
     * 
     * @return
     *     possible object is
     *     {@link RevokeableType }
     *     
     */
    public RevokeableType getRevokeable() {
        return revokeable;
    }

    /**
     * Sets the value of the revokeable property.
     * 
     * @param value
     *     allowed object is
     *     {@link RevokeableType }
     *     
     */
    public void setRevokeable(RevokeableType value) {
        this.revokeable = value;
    }

    /**
     * Gets the value of the shareToPayee property.
     * 
     * @return
     *     possible object is
     *     {@link ShareToPayee }
     *     
     */
    public ShareToPayee getShareToPayee() {
        return shareToPayee;
    }

    /**
     * Sets the value of the shareToPayee property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShareToPayee }
     *     
     */
    public void setShareToPayee(ShareToPayee value) {
        this.shareToPayee = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the blockFund property.
     * 
     * @return
     *     possible object is
     *     {@link BlockFund }
     *     
     */
    public BlockFund getBlockFund() {
        return blockFund;
    }

    /**
     * Sets the value of the blockFund property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlockFund }
     *     
     */
    public void setBlockFund(BlockFund value) {
        this.blockFund = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="rule" use="required" type="{http://npci.org/upi/schema/}amtRuleType" />
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Amount {

        @XmlAttribute(name = "value", required = true)
        protected String value;
        @XmlAttribute(name = "rule", required = true)
        protected AmtRuleType rule;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the rule property.
         * 
         * @return
         *     possible object is
         *     {@link AmtRuleType }
         *     
         */
        public AmtRuleType getRule() {
            return rule;
        }

        /**
         * Sets the value of the rule property.
         * 
         * @param value
         *     allowed object is
         *     {@link AmtRuleType }
         *     
         */
        public void setRule(AmtRuleType value) {
            this.rule = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <attribute name="start" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="end" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Validity {

        @XmlAttribute(name = "start", required = true)
        protected String start;
        @XmlAttribute(name = "end", required = true)
        protected String end;

        /**
         * Gets the value of the start property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStart() {
            return start;
        }

        /**
         * Sets the value of the start property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStart(String value) {
            this.start = value;
        }

        /**
         * Gets the value of the end property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEnd() {
            return end;
        }

        /**
         * Sets the value of the end property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEnd(String value) {
            this.end = value;
        }

    }

}
