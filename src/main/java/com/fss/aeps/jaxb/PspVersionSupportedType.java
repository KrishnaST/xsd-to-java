//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pspVersionSupportedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="pspVersionSupportedType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Version" type="{http://npci.org/upi/schema/}pspVersionType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pspVersionSupportedType", propOrder = {
    "version"
})
public class PspVersionSupportedType {

    @XmlElement(name = "Version", required = true)
    protected PspVersionType version;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link PspVersionType }
     *     
     */
    public PspVersionType getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link PspVersionType }
     *     
     */
    public void setVersion(PspVersionType value) {
        this.version = value;
    }

}
