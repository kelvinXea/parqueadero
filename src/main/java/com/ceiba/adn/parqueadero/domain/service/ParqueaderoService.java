package com.ceiba.adn.parqueadero.domain.service;

import java.util.List;

import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParqueaderoService {
	
	public Factura registrarIngresoVehiculo(Vehiculo vehiculo);
	public Mono<Factura> registrarIngresoVehiculoReactive(VehiculoDTO vehiculo);
	public List<Factura> obtenerVehiculosEnElParqueadero();
	public Flux<VehiculoDTO> obtenerVehiculosEnElParqueaderoReactive();
	public Factura registrarSalidaDeVehiculo(String placa);
	

}
