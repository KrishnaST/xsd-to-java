//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Head" type="{http://npci.org/upi/schema/}headType"/>
 *         <element name="Txn" type="{http://npci.org/upi/schema/}payTrans"/>
 *         <element name="Resp">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="Ref" type="{http://npci.org/upi/schema/}ref" maxOccurs="50" minOccurs="0"/>
 *                   <element name="Consent" type="{http://npci.org/upi/schema/}consentType" minOccurs="0"/>
 *                 </sequence>
 *                 <attribute name="reqMsgId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="result" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="errCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="opType" type="{http://npci.org/upi/schema/}payConstant" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "head",
    "txn",
    "resp"
})
@XmlRootElement(name = "RespChkTxn")
public class RespChkTxn {

    @XmlElement(name = "Head", required = true)
    protected HeadType head;
    @XmlElement(name = "Txn", required = true)
    protected PayTrans txn;
    @XmlElement(name = "Resp", required = true)
    protected RespChkTxn.Resp resp;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link HeadType }
     *     
     */
    public HeadType getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeadType }
     *     
     */
    public void setHead(HeadType value) {
        this.head = value;
    }

    /**
     * Gets the value of the txn property.
     * 
     * @return
     *     possible object is
     *     {@link PayTrans }
     *     
     */
    public PayTrans getTxn() {
        return txn;
    }

    /**
     * Sets the value of the txn property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayTrans }
     *     
     */
    public void setTxn(PayTrans value) {
        this.txn = value;
    }

    /**
     * Gets the value of the resp property.
     * 
     * @return
     *     possible object is
     *     {@link RespChkTxn.Resp }
     *     
     */
    public RespChkTxn.Resp getResp() {
        return resp;
    }

    /**
     * Sets the value of the resp property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespChkTxn.Resp }
     *     
     */
    public void setResp(RespChkTxn.Resp value) {
        this.resp = value;
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
     *       <sequence>
     *         <element name="Ref" type="{http://npci.org/upi/schema/}ref" maxOccurs="50" minOccurs="0"/>
     *         <element name="Consent" type="{http://npci.org/upi/schema/}consentType" minOccurs="0"/>
     *       </sequence>
     *       <attribute name="reqMsgId" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="result" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="errCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="opType" type="{http://npci.org/upi/schema/}payConstant" />
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ref",
        "consent"
    })
    public static class Resp {

        @XmlElement(name = "Ref")
        protected List<Ref> ref;
        @XmlElement(name = "Consent")
        protected ConsentType consent;
        @XmlAttribute(name = "reqMsgId")
        protected String reqMsgId;
        @XmlAttribute(name = "result")
        protected String result;
        @XmlAttribute(name = "errCode")
        protected String errCode;
        @XmlAttribute(name = "opType")
        protected PayConstant opType;

        /**
         * Gets the value of the ref property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the ref property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Ref }
         * 
         * 
         * @return
         *     The value of the ref property.
         */
        public List<Ref> getRef() {
            if (ref == null) {
                ref = new ArrayList<>();
            }
            return this.ref;
        }

        /**
         * Gets the value of the consent property.
         * 
         * @return
         *     possible object is
         *     {@link ConsentType }
         *     
         */
        public ConsentType getConsent() {
            return consent;
        }

        /**
         * Sets the value of the consent property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsentType }
         *     
         */
        public void setConsent(ConsentType value) {
            this.consent = value;
        }

        /**
         * Gets the value of the reqMsgId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReqMsgId() {
            return reqMsgId;
        }

        /**
         * Sets the value of the reqMsgId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReqMsgId(String value) {
            this.reqMsgId = value;
        }

        /**
         * Gets the value of the result property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResult() {
            return result;
        }

        /**
         * Sets the value of the result property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResult(String value) {
            this.result = value;
        }

        /**
         * Gets the value of the errCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrCode() {
            return errCode;
        }

        /**
         * Sets the value of the errCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrCode(String value) {
            this.errCode = value;
        }

        /**
         * Gets the value of the opType property.
         * 
         * @return
         *     possible object is
         *     {@link PayConstant }
         *     
         */
        public PayConstant getOpType() {
            return opType;
        }

        /**
         * Sets the value of the opType property.
         * 
         * @param value
         *     allowed object is
         *     {@link PayConstant }
         *     
         */
        public void setOpType(PayConstant value) {
            this.opType = value;
        }

    }

}
