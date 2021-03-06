package com.ceiba.adn.parqueadero.serviceprovider.trmservice.service;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import com.ceiba.adn.parqueadero.serviceprovider.trmservice.response.TcrmResponse;

public class TCRMServicesInterfaceProxy implements TCRMServicesInterface
{
	private String endpoint = null;
	private TCRMServicesInterface tCRMServicesInterface = null;
	private static final Logger LOGGER = Logger.getLogger( TCRMServicesInterfaceProxy.class.getName() );

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
			((javax.xml.rpc.Stub) tCRMServicesInterface)._setProperty( "javax.xml.rpc.service.endpoint.address",endpoint);

		}
		catch (ServiceException e)
		{
			LOGGER.log(Level.SEVERE, "Service Exception", e);
			
		}
	}

	@Override
	public TcrmResponse queryTCRM(Calendar tcrmQueryAssociatedDate) throws RemoteException {
		return tCRMServicesInterface.queryTCRM(tcrmQueryAssociatedDate);
	}

}