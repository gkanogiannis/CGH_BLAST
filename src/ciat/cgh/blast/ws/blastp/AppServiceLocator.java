/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.blastp.AppServiceLocator
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

package ciat.cgh.blast.ws.blastp;

public class AppServiceLocator extends org.apache.axis.client.Service implements ciat.cgh.blast.ws.blastp.AppService {

    public AppServiceLocator() {
    }


    public AppServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AppServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AppServicePort
    private java.lang.String AppServicePort_address = "https://172.22.55.12:8443/opal2/services/blastp";

    public java.lang.String getAppServicePortAddress() {
        return AppServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AppServicePortWSDDServiceName = "AppServicePort";

    public java.lang.String getAppServicePortWSDDServiceName() {
        return AppServicePortWSDDServiceName;
    }

    public void setAppServicePortWSDDServiceName(java.lang.String name) {
        AppServicePortWSDDServiceName = name;
    }

    public ciat.cgh.blast.ws.blastp.AppServicePortType getAppServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AppServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAppServicePort(endpoint);
    }

    public ciat.cgh.blast.ws.blastp.AppServicePortType getAppServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ciat.cgh.blast.ws.blastp.AppServicePortSoapBindingStub _stub = new ciat.cgh.blast.ws.blastp.AppServicePortSoapBindingStub(portAddress, this);
            _stub.setPortName(getAppServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAppServicePortEndpointAddress(java.lang.String address) {
        AppServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ciat.cgh.blast.ws.blastp.AppServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ciat.cgh.blast.ws.blastp.AppServicePortSoapBindingStub _stub = new ciat.cgh.blast.ws.blastp.AppServicePortSoapBindingStub(new java.net.URL(AppServicePort_address), this);
                _stub.setPortName(getAppServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AppServicePort".equals(inputPortName)) {
            return getAppServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal", "AppService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal", "AppServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AppServicePort".equals(portName)) {
            setAppServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
