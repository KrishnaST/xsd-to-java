//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.24 at 02:25:14 PM IST 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Uses complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Uses"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pi" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pfa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="bio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="bt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="otp" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Uses", propOrder = {
    "pi",
    "pa",
    "pfa",
    "bio",
    "bt",
    "pin",
    "otp"
})
public class Uses {

    @XmlElement(required = true)
    protected String pi;
    @XmlElement(required = true)
    protected String pa;
    @XmlElement(required = true)
    protected String pfa;
    @XmlElement(required = true)
    protected String bio;
    @XmlElement(required = true)
    protected String bt;
    @XmlElement(required = true)
    protected String pin;
    @XmlElement(required = true)
    protected String otp;

    /**
     * Gets the value of the pi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPi() {
        return pi;
    }

    /**
     * Sets the value of the pi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPi(String value) {
        this.pi = value;
    }

    /**
     * Gets the value of the pa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPa() {
        return pa;
    }

    /**
     * Sets the value of the pa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPa(String value) {
        this.pa = value;
    }

    /**
     * Gets the value of the pfa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPfa() {
        return pfa;
    }

    /**
     * Sets the value of the pfa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPfa(String value) {
        this.pfa = value;
    }

    /**
     * Gets the value of the bio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the value of the bio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBio(String value) {
        this.bio = value;
    }

    /**
     * Gets the value of the bt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBt() {
        return bt;
    }

    /**
     * Sets the value of the bt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBt(String value) {
        this.bt = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin(String value) {
        this.pin = value;
    }

    /**
     * Gets the value of the otp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtp() {
        return otp;
    }

    /**
     * Sets the value of the otp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtp(String value) {
        this.otp = value;
    }

}
