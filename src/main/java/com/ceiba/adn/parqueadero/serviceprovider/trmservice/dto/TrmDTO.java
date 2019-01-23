package com.ceiba.adn.parqueadero.serviceprovider.trmservice.dto;

import java.time.LocalDateTime;

public class TrmDTO {
	
	private double valor;
	private LocalDateTime vigenciaInicio;
	private LocalDateTime vigenciaFin;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDateTime getVigenciaInicio() {
		return vigenciaInicio;
	}
	public void setVigenciaInicio(LocalDateTime vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}
	public LocalDateTime getVigenciaFin() {
		return vigenciaFin;
	}
	public void setVigenciaFin(LocalDateTime vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}
	
	

}
