package com.ceiba.adn.parqueadero.builder;

import java.time.LocalDateTime;

import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class VehiculoTestDataBuilder {
	
	private static final String PLACA = "EUWEWE";
	
	private static final TipoVehiculo TIPO_VEHICULO = TipoVehiculo.CARRO;
	
	private static final Integer CC = 0;
	
	private static final LocalDateTime FECHA_ENTRADA = LocalDateTime.now();
	
	private String placa;
	
	private TipoVehiculo tipoVehiculo;
	
	private Integer cc;
	
	private LocalDateTime fechaEntrada;

	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.tipoVehiculo = TIPO_VEHICULO;
		this.cc = CC;
		this.fechaEntrada = FECHA_ENTRADA;
	}	
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder withfechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}
	
	public VehiculoTestDataBuilder withCc(Integer cc) {
		this.cc = cc;
		return this;
	}
	
	public VehiculoTestDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public VehiculoDTO buildVehiculoDTO() {
		
		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		
		vehiculoDTO.setCc(this.cc);
		vehiculoDTO.setPlaca(this.placa);
		vehiculoDTO.setTipoVehiculo(this.tipoVehiculo);
		vehiculoDTO.setFechaEntrada(this.fechaEntrada);
		
		return vehiculoDTO;
		
	}
	
	

}
