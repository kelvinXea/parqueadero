package com.ceiba.adn.parqueadero.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

@Entity
@Table
public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
	private Long id;
	
	@Column(length = 6, nullable = false)
	private String placa;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrada;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	
	@Column(nullable = false)
	private Integer cc;
	
	@Column
	private Integer pagoTotal;
	
	@Column(nullable = false)
	private TipoVehiculo tipoVehiculo;
	
	@Column(nullable = false)
	private Boolean isCompleto;
	

	public Factura() {

	}

	public Factura(String placa, Date fechaEntrada, TipoVehiculo tipoVehiculo) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.tipoVehiculo = tipoVehiculo;
		this.cc = 0;
		this.isCompleto = false;
	}
	
	public Factura(String placa, Date fechaEntrada, TipoVehiculo tipoVehiculo, Integer cc) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.tipoVehiculo = tipoVehiculo;
		this.isCompleto = false;
		if(this.tipoVehiculo == TipoVehiculo.MOTO)
		this.cc = cc;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		if(this.tipoVehiculo == TipoVehiculo.MOTO)
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

	public boolean isCompleto() {
		return isCompleto;
	}

	public void setCompleto(boolean isCompleto) {
		this.isCompleto = isCompleto;
	}
	
	
	

}
