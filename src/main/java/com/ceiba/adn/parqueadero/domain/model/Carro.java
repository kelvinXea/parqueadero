package com.ceiba.adn.parqueadero.domain.model;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class Carro extends Vehiculo {
	
	private static final Integer VALOR_HORA = 1000;
	private static final Integer VALOR_DIA = 8000;
	
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
		return VALOR_HORA;
	}

	@Override
	public Integer getValorDia() {
		return VALOR_DIA;
	}

	@Override
	public Integer getCc() {
		return 0;
	}
	
	

}
