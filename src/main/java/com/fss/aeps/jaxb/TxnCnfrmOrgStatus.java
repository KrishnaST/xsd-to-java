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
 * <p>Java class for txnCnfrmOrgStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="txnCnfrmOrgStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SUCCESS"/&gt;
 *     &lt;enumeration value="FAILURE"/&gt;
 *     &lt;enumeration value="PENDING"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "txnCnfrmOrgStatus")
@XmlEnum
public enum TxnCnfrmOrgStatus {

    SUCCESS,
    FAILURE,
    PENDING;

    public String value() {
        return name();
    }

    public static TxnCnfrmOrgStatus fromValue(String v) {
        return valueOf(v);
    }

}
