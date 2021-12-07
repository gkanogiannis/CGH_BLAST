/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.tblastx.types.JobStatisticsType
 *
 * Copyright (C) 2021 Anestis Gkanogiannis <anestis@gkanogiannis.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */

package ciat.cgh.blast.ws.tblastx.types;

public class JobStatisticsType  implements java.io.Serializable {
    private java.util.Calendar startTime;

    private java.util.Calendar activationTime;

    private java.util.Calendar completionTime;

    public JobStatisticsType() {
    }

    public JobStatisticsType(
           java.util.Calendar startTime,
           java.util.Calendar activationTime,
           java.util.Calendar completionTime) {
           this.startTime = startTime;
           this.activationTime = activationTime;
           this.completionTime = completionTime;
    }


    /**
     * Gets the startTime value for this JobStatisticsType.
     * 
     * @return startTime
     */
    public java.util.Calendar getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this JobStatisticsType.
     * 
     * @param startTime
     */
    public void setStartTime(java.util.Calendar startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the activationTime value for this JobStatisticsType.
     * 
     * @return activationTime
     */
    public java.util.Calendar getActivationTime() {
        return activationTime;
    }


    /**
     * Sets the activationTime value for this JobStatisticsType.
     * 
     * @param activationTime
     */
    public void setActivationTime(java.util.Calendar activationTime) {
        this.activationTime = activationTime;
    }


    /**
     * Gets the completionTime value for this JobStatisticsType.
     * 
     * @return completionTime
     */
    public java.util.Calendar getCompletionTime() {
        return completionTime;
    }


    /**
     * Sets the completionTime value for this JobStatisticsType.
     * 
     * @param completionTime
     */
    public void setCompletionTime(java.util.Calendar completionTime) {
        this.completionTime = completionTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobStatisticsType)) return false;
        JobStatisticsType other = (JobStatisticsType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.startTime==null && other.getStartTime()==null) || 
             (this.startTime!=null &&
              this.startTime.equals(other.getStartTime()))) &&
            ((this.activationTime==null && other.getActivationTime()==null) || 
             (this.activationTime!=null &&
              this.activationTime.equals(other.getActivationTime()))) &&
            ((this.completionTime==null && other.getCompletionTime()==null) || 
             (this.completionTime!=null &&
              this.completionTime.equals(other.getCompletionTime())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getStartTime() != null) {
            _hashCode += getStartTime().hashCode();
        }
        if (getActivationTime() != null) {
            _hashCode += getActivationTime().hashCode();
        }
        if (getCompletionTime() != null) {
            _hashCode += getCompletionTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobStatisticsType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobStatisticsType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "completionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
