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
 * <p>Java class for credsAllowedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="credsAllowedType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="type" use="required" type="{http://npci.org/upi/schema/}credType" />
 *       <attribute name="subType" use="required" type="{http://npci.org/upi/schema/}credSubType" />
 *       <attribute name="dType" use="required" type="{http://npci.org/upi/schema/}credDataTypeConstant" />
 *       <attribute name="dLength" use="required" type="{http://npci.org/upi/schema/}credLengthConstant" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credsAllowedType")
public class CredsAllowedType {

    @XmlAttribute(name = "type", required = true)
    protected CredType type;
    @XmlAttribute(name = "subType", required = true)
    protected CredSubType subType;
    @XmlAttribute(name = "dType", required = true)
    protected CredDataTypeConstant dType;
    @XmlAttribute(name = "dLength", required = true)
    protected int dLength;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CredType }
     *     
     */
    public CredType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredType }
     *     
     */
    public void setType(CredType value) {
        this.type = value;
    }

    /**
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link CredSubType }
     *     
     */
    public CredSubType getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredSubType }
     *     
     */
    public void setSubType(CredSubType value) {
        this.subType = value;
    }

    /**
     * Gets the value of the dType property.
     * 
     * @return
     *     possible object is
     *     {@link CredDataTypeConstant }
     *     
     */
    public CredDataTypeConstant getDType() {
        return dType;
    }

    /**
     * Sets the value of the dType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredDataTypeConstant }
     *     
     */
    public void setDType(CredDataTypeConstant value) {
        this.dType = value;
    }

    /**
     * Gets the value of the dLength property.
     * 
     */
    public int getDLength() {
        return dLength;
    }

    /**
     * Sets the value of the dLength property.
     * 
     */
    public void setDLength(int value) {
        this.dLength = value;
    }

}
