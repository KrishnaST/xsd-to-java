//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.24 at 02:25:14 PM IST 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for identifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="identifierType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="subCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="mid" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="sid" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="tid" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="merchantType" type="{http://npci.org/upi/schema/}merchantIdentifierType" /&gt;
 *       &lt;attribute name="merchantGenre" type="{http://npci.org/upi/schema/}merchantGenreType" /&gt;
 *       &lt;attribute name="onBoardingType" type="{http://npci.org/upi/schema/}merchantOnBoardingType" /&gt;
 *       &lt;attribute name="regId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="pinCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="tier" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="merchantLoc" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="merchantInstCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identifierType")
public class IdentifierType {

    @XmlAttribute(name = "subCode")
    protected String subCode;
    @XmlAttribute(name = "mid")
    protected String mid;
    @XmlAttribute(name = "sid")
    protected String sid;
    @XmlAttribute(name = "tid")
    protected String tid;
    @XmlAttribute(name = "merchantType")
    protected MerchantIdentifierType merchantType;
    @XmlAttribute(name = "merchantGenre")
    protected MerchantGenreType merchantGenre;
    @XmlAttribute(name = "onBoardingType")
    protected MerchantOnBoardingType onBoardingType;
    @XmlAttribute(name = "regId")
    protected String regId;
    @XmlAttribute(name = "pinCode")
    protected String pinCode;
    @XmlAttribute(name = "tier")
    protected String tier;
    @XmlAttribute(name = "merchantLoc")
    protected String merchantLoc;
    @XmlAttribute(name = "merchantInstCode")
    protected String merchantInstCode;

    /**
     * Gets the value of the subCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * Sets the value of the subCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubCode(String value) {
        this.subCode = value;
    }

    /**
     * Gets the value of the mid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMid() {
        return mid;
    }

    /**
     * Sets the value of the mid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMid(String value) {
        this.mid = value;
    }

    /**
     * Gets the value of the sid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSid() {
        return sid;
    }

    /**
     * Sets the value of the sid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSid(String value) {
        this.sid = value;
    }

    /**
     * Gets the value of the tid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTid() {
        return tid;
    }

    /**
     * Sets the value of the tid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTid(String value) {
        this.tid = value;
    }

    /**
     * Gets the value of the merchantType property.
     * 
     * @return
     *     possible object is
     *     {@link MerchantIdentifierType }
     *     
     */
    public MerchantIdentifierType getMerchantType() {
        return merchantType;
    }

    /**
     * Sets the value of the merchantType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MerchantIdentifierType }
     *     
     */
    public void setMerchantType(MerchantIdentifierType value) {
        this.merchantType = value;
    }

    /**
     * Gets the value of the merchantGenre property.
     * 
     * @return
     *     possible object is
     *     {@link MerchantGenreType }
     *     
     */
    public MerchantGenreType getMerchantGenre() {
        return merchantGenre;
    }

    /**
     * Sets the value of the merchantGenre property.
     * 
     * @param value
     *     allowed object is
     *     {@link MerchantGenreType }
     *     
     */
    public void setMerchantGenre(MerchantGenreType value) {
        this.merchantGenre = value;
    }

    /**
     * Gets the value of the onBoardingType property.
     * 
     * @return
     *     possible object is
     *     {@link MerchantOnBoardingType }
     *     
     */
    public MerchantOnBoardingType getOnBoardingType() {
        return onBoardingType;
    }

    /**
     * Sets the value of the onBoardingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MerchantOnBoardingType }
     *     
     */
    public void setOnBoardingType(MerchantOnBoardingType value) {
        this.onBoardingType = value;
    }

    /**
     * Gets the value of the regId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegId() {
        return regId;
    }

    /**
     * Sets the value of the regId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegId(String value) {
        this.regId = value;
    }

    /**
     * Gets the value of the pinCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Sets the value of the pinCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinCode(String value) {
        this.pinCode = value;
    }

    /**
     * Gets the value of the tier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTier() {
        return tier;
    }

    /**
     * Sets the value of the tier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTier(String value) {
        this.tier = value;
    }

    /**
     * Gets the value of the merchantLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantLoc() {
        return merchantLoc;
    }

    /**
     * Sets the value of the merchantLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantLoc(String value) {
        this.merchantLoc = value;
    }

    /**
     * Gets the value of the merchantInstCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantInstCode() {
        return merchantInstCode;
    }

    /**
     * Sets the value of the merchantInstCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantInstCode(String value) {
        this.merchantInstCode = value;
    }

}
