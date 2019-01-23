package com.ceiba.adn.parqueadero.serviceprovider.trmservice.service;

import java.rmi.RemoteException;

import com.ceiba.adn.parqueadero.serviceprovider.trmservice.response.TcrmResponse;

public interface TCRMServicesInterface extends java.rmi.Remote 
{
    public TcrmResponse queryTCRM(java.util.Calendar tcrmQueryAssociatedDate) throws RemoteException;
}
