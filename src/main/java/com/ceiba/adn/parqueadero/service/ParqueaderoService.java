package com.ceiba.adn.parqueadero.service;

import java.util.List;

import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;

public interface ParqueaderoService {
	
	public Factura registrarIngresoVehiculo(Vehiculo vehiculo);
	public List<Factura> obtenerVehiculosEnElParqueadero();
	public Factura registrarSalidaDeVehiculo(String placa);

}
