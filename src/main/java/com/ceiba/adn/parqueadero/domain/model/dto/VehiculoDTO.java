package com.ceiba.adn.parqueadero.domain.model.dto;


import java.util.Date;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class VehiculoDTO {	
	
	private String placa;
	
	private TipoVehiculo tipoVehiculo;
	
	private Integer cc;
	
	private Date fechaEntrada;

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

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	
	
	
	

}
