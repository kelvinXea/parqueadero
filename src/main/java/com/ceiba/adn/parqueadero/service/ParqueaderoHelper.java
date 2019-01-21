package com.ceiba.adn.parqueadero.service;

import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;


public interface ParqueaderoHelper {
	
	boolean existeVehiculoEnParqueadero (String placa);
	boolean puedeEntrarEnElParqueadero(String placa);
	boolean parqueaderoEstaLleno(TipoVehiculo tipoVehiculo);
	Factura generarFacturaEntrada(Vehiculo vehiculo);
	Factura generarFacturaSalida(Factura factura);

}
