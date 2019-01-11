package com.ceiba.adn.parqueadero.model.entity;

import com.ceiba.adn.parqueadero.model.dto.enums.TipoVehiculo;

public class Carro extends Vehiculo {
	
	private static final Integer valorHora = 1000;
	private static final Integer valorDia = 8000;

	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.CARRO;
	}

	@Override
	public Integer getValorHora() {
		return valorHora;
	}

	@Override
	public Integer getValorDia() {
		return valorDia;
	}
	
	

}
