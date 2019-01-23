package com.ceiba.adn.parqueadero.domain.model;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class Moto extends Vehiculo {

	private static final Integer VALOR_HORA = 500;
	private static final Integer VALOR_DIA = 4000;
	private Integer cc;

	public Moto(String placa, Integer cc) {
		this.placa = placa;
		this.cc = cc;
	}
	
	public Moto() {
		
	}

	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.MOTO;
	}

	@Override
	public Integer getValorHora() {
		return VALOR_HORA;
	}

	@Override
	public Integer getValorDia() {
		return VALOR_DIA;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

}
