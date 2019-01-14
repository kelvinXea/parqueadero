package com.ceiba.adn.parqueadero.domain.model.dto;


import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class VehiculoDTO {	
	
	private String placa;
	
	private TipoVehiculo tipoVehiculo;
	
	private Integer cc;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}
	
	
	
	
	

}
