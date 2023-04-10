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
 * <p>Java class for merchantType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="merchantType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Identifier" type="{http://npci.org/upi/schema/}identifierType" minOccurs="0"/>
 *         <element name="Name" type="{http://npci.org/upi/schema/}nameType" minOccurs="0"/>
 *         <element name="Ownership" type="{http://npci.org/upi/schema/}merchantOwnership"/>
 *         <element name="Invoice" type="{http://npci.org/upi/schema/}invoiceType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "merchantType", propOrder = {
    "identifier",
    "name",
    "ownership",
    "invoice"
})
public class MerchantType {

    @XmlElement(name = "Identifier")
    protected IdentifierType identifier;
    @XmlElement(name = "Name")
    protected NameType name;
    @XmlElement(name = "Ownership", required = true)
    protected MerchantOwnership ownership;
    @XmlElement(name = "Invoice", required = true)
    protected InvoiceType invoice;

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     */
    public void setIdentifier(IdentifierType value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setName(NameType value) {
        this.name = value;
    }

    /**
     * Gets the value of the ownership property.
     * 
     * @return
     *     possible object is
     *     {@link MerchantOwnership }
     *     
     */
    public MerchantOwnership getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     * 
     * @param value
     *     allowed object is
     *     {@link MerchantOwnership }
     *     
     */
    public void setOwnership(MerchantOwnership value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the invoice property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceType }
     *     
     */
    public InvoiceType getInvoice() {
        return invoice;
    }

    /**
     * Sets the value of the invoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceType }
     *     
     */
    public void setInvoice(InvoiceType value) {
        this.invoice = value;
    }

}
