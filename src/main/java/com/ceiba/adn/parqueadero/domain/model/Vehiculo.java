package com.ceiba.adn.parqueadero.domain.model;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public abstract class Vehiculo {
	
	protected String placa;
	
	public abstract TipoVehiculo getTipoVehiculo();
	public abstract Integer getValorHora();
	public abstract Integer getValorDia();
	public abstract Integer getCc();

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	

}
