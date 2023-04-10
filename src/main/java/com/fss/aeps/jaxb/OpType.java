//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for opType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="opType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="ADD"/>
 *     <enumeration value="MODIFY"/>
 *     <enumeration value="UPDATE"/>
 *     <enumeration value="REMOVE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "opType")
@XmlEnum
public enum OpType {

    ADD,
    MODIFY,
    UPDATE,
    REMOVE;

    public String value() {
        return name();
    }

    public static OpType fromValue(String v) {
        return valueOf(v);
    }

}
