package com.ceiba.adn.parqueadero.domain.model.dto.enums;

public enum TipoVehiculo {
	MOTO("Moto"),
	CARRO("Carro");

	private String label;
	
	TipoVehiculo(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	
	
}