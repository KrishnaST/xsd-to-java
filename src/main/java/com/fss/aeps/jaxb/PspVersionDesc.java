//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.24 at 02:25:14 PM IST 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pspVersionDesc.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="pspVersionDesc"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Basic UPI 1.0 version"/&gt;
 *     &lt;enumeration value="UPI 2.0 version: All tag level changes"/&gt;
 *     &lt;enumeration value="UPI 2.0 Mandate"/&gt;
 *     &lt;enumeration value="REFUND"/&gt;
 *     &lt;enumeration value="AADHAAR"/&gt;
 *     &lt;enumeration value="VALCUST"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "pspVersionDesc")
@XmlEnum
public enum PspVersionDesc {

    @XmlEnumValue("Basic UPI 1.0 version")
    BASIC_UPI_1_0_VERSION("Basic UPI 1.0 version"),
    @XmlEnumValue("UPI 2.0 version: All tag level changes")
    UPI_2_0_VERSION_ALL_TAG_LEVEL_CHANGES("UPI 2.0 version: All tag level changes"),
    @XmlEnumValue("UPI 2.0 Mandate")
    UPI_2_0_MANDATE("UPI 2.0 Mandate"),
    REFUND("REFUND"),
    AADHAAR("AADHAAR"),
    VALCUST("VALCUST");
    private final String value;

    PspVersionDesc(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PspVersionDesc fromValue(String v) {
        for (PspVersionDesc c: PspVersionDesc.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
