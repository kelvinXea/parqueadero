package com.ceiba.adn.parqueadero.domain.model.dto;

import java.util.Date;



import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class FacturaDTO {
	
	private String placa;
	
	private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private Integer cc;
	
	private Integer pagoTotal;
	
	private TipoVehiculo tipoVehiculo;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	public Integer getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(Integer pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	

}
