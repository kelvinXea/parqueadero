package com.ceiba.adn.parqueadero.domain.model.dto;

import java.time.LocalDateTime;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class FacturaDTO {
	
	private String placa;
	
	private LocalDateTime fechaEntrada;
	
	private LocalDateTime fechaSalida;
	
	private Integer cc;
	
	private Integer pagoTotal;
	
	private TipoVehiculo tipoVehiculo;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
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
