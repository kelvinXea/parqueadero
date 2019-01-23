package com.ceiba.adn.parqueadero.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

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
	// TODO implementar mensajes en los properties
	@Size(max = 10, message = "La placa no puede ser mayor a 10 caracteres")
	@Size(min = 2, message = "La placa no puede ser menor a 2 caracteres")
	@Column(nullable = false)
	private String placa;
	
    @Version
    @Column(nullable = false)
    private Long version;
	
	@Column(nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fechaEntrada;
	
	@Column
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fechaSalida;
	
	@Max(value = 1500, message = "El cilindraje no puede ser mayor a 1500")
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

	public Factura(String placa, LocalDateTime fechaEntrada, TipoVehiculo tipoVehiculo) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.tipoVehiculo = tipoVehiculo;
		this.cc = 0;
		this.isCompleto = false;
	}
	
	public Factura(String placa, LocalDateTime fechaEntrada, TipoVehiculo tipoVehiculo, Integer cc) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.tipoVehiculo = tipoVehiculo;
		this.isCompleto = false;
		if (this.tipoVehiculo == TipoVehiculo.MOTO)
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
