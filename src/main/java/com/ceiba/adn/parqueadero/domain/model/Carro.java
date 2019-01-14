package com.ceiba.adn.parqueadero.domain.model;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class Carro extends Vehiculo {
	
	private static final Integer valorHora = 1000;
	private static final Integer valorDia = 8000;
	
	public Carro(String placa) {
		this.placa = placa;
	}
	
	public Carro() {

	}

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

	@Override
	public Integer getCc() {
		return 0;
	}
	
	

}
