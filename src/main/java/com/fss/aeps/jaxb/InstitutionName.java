//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for institutionName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="institutionName">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="acNum" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="ifsc" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "institutionName")
public class InstitutionName {

    @XmlAttribute(name = "value", required = true)
    protected String value;
    @XmlAttribute(name = "acNum", required = true)
    protected String acNum;
    @XmlAttribute(name = "ifsc", required = true)
    protected String ifsc;

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
     * Gets the value of the acNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcNum() {
        return acNum;
    }

    /**
     * Sets the value of the acNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcNum(String value) {
        this.acNum = value;
    }

    /**
     * Gets the value of the ifsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfsc() {
        return ifsc;
    }

    /**
     * Sets the value of the ifsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfsc(String value) {
        this.ifsc = value;
    }

}
