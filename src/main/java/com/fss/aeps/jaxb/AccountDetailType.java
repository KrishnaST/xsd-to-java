//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountDetailType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="accountDetailType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="IIN"/>
 *     <enumeration value="UIDNUM"/>
 *     <enumeration value="IFSC"/>
 *     <enumeration value="ACTYPE"/>
 *     <enumeration value="ACNUM"/>
 *     <enumeration value="MMID"/>
 *     <enumeration value="MOBNUM"/>
 *     <enumeration value="CARDNUM"/>
 *     <enumeration value="CREDIT"/>
 *     <enumeration value="PPIWALLET"/>
 *     <enumeration value="BANKWALLET"/>
 *     <enumeration value="SEMICLOSEDBANKWALLET"/>
 *     <enumeration value="SEMICLOSEDPPIWALLET"/>
 *     <enumeration value="NRO"/>
 *     <enumeration value="UIDTOKEN"/>
 *     <enumeration value="VID"/>
 *     <enumeration value="CIF"/>
 *     <enumeration value="REFKEY"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "accountDetailType")
@XmlEnum
public enum AccountDetailType {

    IIN,
    UIDNUM,
    IFSC,
    ACTYPE,
    ACNUM,
    MMID,
    MOBNUM,
    CARDNUM,
    CREDIT,
    PPIWALLET,
    BANKWALLET,
    SEMICLOSEDBANKWALLET,
    SEMICLOSEDPPIWALLET,
    NRO,
    UIDTOKEN,
    VID,
    CIF,
    REFKEY;

    public String value() {
        return name();
    }

    public static AccountDetailType fromValue(String v) {
        return valueOf(v);
    }

}
