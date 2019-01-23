package com.ceiba.adn.parqueadero.serviceprovider.trmservice.service;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;

public class TCRMServicesWebServiceLocator extends Service implements TCRMServicesWebService {

	private static final long serialVersionUID = 1L;

	private String tCRMServicesWebServicePortAddress = "http://AlexChacon:8080/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";

	public String getTCRMServicesWebServicePortAddress() {
		return tCRMServicesWebServicePortAddress;
	}

	private String tCRMServicesWebServicePortWSDDServiceName = "TCRMServicesWebServicePort";

	public String getTCRMServicesWebServicePortWSDDServiceName() {
		return tCRMServicesWebServicePortWSDDServiceName;
	}

	public TCRMServicesInterface getTCRMServicesWebServicePort() throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(tCRMServicesWebServicePortAddress);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getTCRMServicesWebServicePort(endpoint);
	}

	public TCRMServicesInterface getTCRMServicesWebServicePort(URL portAddress) throws ServiceException {
		try {
			TCRMServicesWebServiceSoapBindingStub stub = new TCRMServicesWebServiceSoapBindingStub(portAddress, this);
			stub.setPortName(getTCRMServicesWebServicePortWSDDServiceName());
			return stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}


}
