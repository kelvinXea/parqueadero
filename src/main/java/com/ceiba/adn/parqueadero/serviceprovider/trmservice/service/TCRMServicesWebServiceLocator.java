package com.ceiba.adn.parqueadero.serviceprovider.trmservice.service;

import java.net.URL;
import java.rmi.Remote;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;

public class TCRMServicesWebServiceLocator extends Service implements TCRMServicesWebService {

	private static final long serialVersionUID = 1L;

	public TCRMServicesWebServiceLocator() {
	}

	private String TCRMServicesWebServicePort_address = "http://AlexChacon:8080/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";

	public String getTCRMServicesWebServicePortAddress() {
		return TCRMServicesWebServicePort_address;
	}

	private String TCRMServicesWebServicePortWSDDServiceName = "TCRMServicesWebServicePort";

	public String getTCRMServicesWebServicePortWSDDServiceName() {
		return TCRMServicesWebServicePortWSDDServiceName;
	}

	public TCRMServicesInterface getTCRMServicesWebServicePort() throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(TCRMServicesWebServicePort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getTCRMServicesWebServicePort(endpoint);
	}

	public TCRMServicesInterface getTCRMServicesWebServicePort(URL portAddress) throws ServiceException {
		try {
			TCRMServicesWebServiceSoapBindingStub _stub = new TCRMServicesWebServiceSoapBindingStub(portAddress, this);
			_stub.setPortName(getTCRMServicesWebServicePortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	private java.util.HashSet<javax.xml.namespace.QName> ports = null;

}
