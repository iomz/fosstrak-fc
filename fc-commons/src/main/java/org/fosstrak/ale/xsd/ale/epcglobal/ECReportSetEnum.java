/*
 * Copyright (C) 2007 ETH Zurich
 * 
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 * 
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

/**
 * ECReportSetEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package org.accada.ale.xsd.ale.epcglobal;

public class ECReportSetEnum implements java.io.Serializable {
    private org.apache.axis.types.NCName _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ECReportSetEnum(org.apache.axis.types.NCName value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final org.apache.axis.types.NCName _CURRENT = new org.apache.axis.types.NCName("CURRENT");
    public static final org.apache.axis.types.NCName _ADDITIONS = new org.apache.axis.types.NCName("ADDITIONS");
    public static final org.apache.axis.types.NCName _DELETIONS = new org.apache.axis.types.NCName("DELETIONS");
    public static final ECReportSetEnum CURRENT = new ECReportSetEnum(_CURRENT);
    public static final ECReportSetEnum ADDITIONS = new ECReportSetEnum(_ADDITIONS);
    public static final ECReportSetEnum DELETIONS = new ECReportSetEnum(_DELETIONS);
    public org.apache.axis.types.NCName getValue() { return _value_;}
    public static ECReportSetEnum fromValue(org.apache.axis.types.NCName value)
          throws java.lang.IllegalArgumentException {
        ECReportSetEnum enumeration = (ECReportSetEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ECReportSetEnum fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        try {
            return fromValue(new org.apache.axis.types.NCName(value));
        } catch (Exception e) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_.toString();}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ECReportSetEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:epcglobal:ale:xsd:1", "ECReportSetEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
