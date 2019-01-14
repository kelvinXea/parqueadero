package com.ceiba.adn.parqueadero.service;

import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;


public interface ParqueaderoHelper {
	
	boolean ExisteVehiculoEnParqueadero (String placa);
	boolean puedeEntrarEnElParqueadero(String placa);
	boolean parqueaderoEstaLleno(TipoVehiculo tipoVehiculo);
	Factura generarFacturaSalida(Factura factura);

}
