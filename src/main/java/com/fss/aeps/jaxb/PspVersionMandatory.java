//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.24 at 02:25:14 PM IST 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pspVersionMandatory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="pspVersionMandatory"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TRUE"/&gt;
 *     &lt;enumeration value="FALSE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "pspVersionMandatory")
@XmlEnum
public enum PspVersionMandatory {

    TRUE,
    FALSE;

    public String value() {
        return name();
    }

    public static PspVersionMandatory fromValue(String v) {
        return valueOf(v);
    }

}
