package com.ceiba.adn.parqueadero.serviceprovider.trmservice.service;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import com.ceiba.adn.parqueadero.serviceprovider.trmservice.response.TcrmResponse;

public class TCRMServicesInterfaceProxy implements TCRMServicesInterface
{
	private String endpoint = null;
	private TCRMServicesInterface tCRMServicesInterface = null;

	public TCRMServicesInterfaceProxy(String endpoint)
	{
		this.endpoint = endpoint;
		initTCRMServicesInterfaceProxy();
	}

	private void initTCRMServicesInterfaceProxy()
	{
		try
		{
			tCRMServicesInterface = (new TCRMServicesWebServiceLocator()) .getTCRMServicesWebServicePort();
			if (tCRMServicesInterface != null)
			{
				if (endpoint != null)
					((javax.xml.rpc.Stub) tCRMServicesInterface)._setProperty( "javax.xml.rpc.service.endpoint.address",endpoint);
				else
					endpoint = (String) ((javax.xml.rpc.Stub) tCRMServicesInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		}
		catch (ServiceException serviceException)
		{
			serviceException.printStackTrace();
		}
	}

	@Override
	public TcrmResponse queryTCRM(Calendar tcrmQueryAssociatedDate) throws RemoteException {
		if (tCRMServicesInterface == null)
			initTCRMServicesInterfaceProxy();
		return tCRMServicesInterface.queryTCRM(tcrmQueryAssociatedDate);
	}

}