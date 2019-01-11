package com.ceiba.adn.parqueadero.model.entity;

import com.ceiba.adn.parqueadero.model.dto.enums.TipoVehiculo;

public class Moto extends Vehiculo {

	private static final Integer valorHora = 500;
	private static final Integer valorDia = 4000;
	private Integer cc;
	
	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.MOTO;
	}

	@Override
	public Integer getValorHora() {
		return valorHora;
	}

	@Override
	public Integer getValorDia() {
		return valorDia;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}
	
	

}
