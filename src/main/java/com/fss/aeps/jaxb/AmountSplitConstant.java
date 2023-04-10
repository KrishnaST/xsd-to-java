//
// This file was generated by the Eclipse Implementation of JAXB, v4.0.1 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
//


package com.fss.aeps.jaxb;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for amountSplitConstant.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="amountSplitConstant">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="PURCHASE"/>
 *     <enumeration value="CASHBACK"/>
 *     <enumeration value="PARAMOUNT"/>
 *     <enumeration value="GST"/>
 *     <enumeration value="CGST"/>
 *     <enumeration value="SGST"/>
 *     <enumeration value="IGST"/>
 *     <enumeration value="CESS"/>
 *     <enumeration value="GSTINCENTIVE"/>
 *     <enumeration value="GSTPCT"/>
 *     <enumeration value="TIPS"/>
 *     <enumeration value="CONFEE"/>
 *     <enumeration value="DISCPCT"/>
 *     <enumeration value="CONPCT"/>
 *     <enumeration value="DISCOUNT"/>
 *     <enumeration value="baseAmount"/>
 *     <enumeration value="baseCurr"/>
 *     <enumeration value="FX"/>
 *     <enumeration value="Mkup"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "amountSplitConstant")
@XmlEnum
public enum AmountSplitConstant {

    PURCHASE("PURCHASE"),
    CASHBACK("CASHBACK"),
    PARAMOUNT("PARAMOUNT"),
    GST("GST"),
    CGST("CGST"),
    SGST("SGST"),
    IGST("IGST"),
    CESS("CESS"),
    GSTINCENTIVE("GSTINCENTIVE"),
    GSTPCT("GSTPCT"),
    TIPS("TIPS"),
    CONFEE("CONFEE"),
    DISCPCT("DISCPCT"),
    CONPCT("CONPCT"),
    DISCOUNT("DISCOUNT"),
    @XmlEnumValue("baseAmount")
    BASE_AMOUNT("baseAmount"),
    @XmlEnumValue("baseCurr")
    BASE_CURR("baseCurr"),
    FX("FX"),
    @XmlEnumValue("Mkup")
    MKUP("Mkup");
    private final String value;

    AmountSplitConstant(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AmountSplitConstant fromValue(String v) {
        for (AmountSplitConstant c: AmountSplitConstant.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
