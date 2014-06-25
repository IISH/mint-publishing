/**
 * CatwalkWebserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsdl.catwalk;

public class CatwalkWebserviceLocator extends org.apache.axis.client.Service implements wsdl.catwalk.CatwalkWebservice {

    public CatwalkWebserviceLocator() {
    }


    public CatwalkWebserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CatwalkWebserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CatwalkWebservicePort
    private java.lang.String CatwalkWebservicePort_address = "http://soap.catwalkpictures.com/index.php";

    public java.lang.String getCatwalkWebservicePortAddress() {
        return CatwalkWebservicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CatwalkWebservicePortWSDDServiceName = "catwalk webservicePort";

    public java.lang.String getCatwalkWebservicePortWSDDServiceName() {
        return CatwalkWebservicePortWSDDServiceName;
    }

    public void setCatwalkWebservicePortWSDDServiceName(java.lang.String name) {
        CatwalkWebservicePortWSDDServiceName = name;
    }

    public wsdl.catwalk.CatwalkWebservicePortType getCatwalkWebservicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CatwalkWebservicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCatwalkWebservicePort(endpoint);
    }

    public wsdl.catwalk.CatwalkWebservicePortType getCatwalkWebservicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            wsdl.catwalk.CatwalkWebserviceBindingStub _stub = new wsdl.catwalk.CatwalkWebserviceBindingStub(portAddress, this);
            _stub.setPortName(getCatwalkWebservicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCatwalkWebservicePortEndpointAddress(java.lang.String address) {
        CatwalkWebservicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (wsdl.catwalk.CatwalkWebservicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                wsdl.catwalk.CatwalkWebserviceBindingStub _stub = new wsdl.catwalk.CatwalkWebserviceBindingStub(new java.net.URL(CatwalkWebservicePort_address), this);
                _stub.setPortName(getCatwalkWebservicePortWSDDServiceName());
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
        if ("catwalk webservicePort".equals(inputPortName)) {
            return getCatwalkWebservicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("catwalk.wsdl", "catwalk webservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("catwalk.wsdl", "catwalk webservicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CatwalkWebservicePort".equals(portName)) {
            setCatwalkWebservicePortEndpointAddress(address);
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
