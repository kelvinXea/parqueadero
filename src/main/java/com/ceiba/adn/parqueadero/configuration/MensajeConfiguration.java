package com.ceiba.adn.parqueadero.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mensajes.properties")
public class MensajeConfiguration {

	@Value("${parqueadero.vehiculoEnParqueadero}")
	private String vehiculoEnParqueadero;

	@Value("${parqueadero.vehiculoNoPuedeEntrar}")
	private String vehiculoNoPuedeEntrar;

	@Value("${parqueadero.parqueaderoLleno}")
	private String parqueaderoLleno;

	@Value("${parqueadero.vehiculoRegistrado}")
	private String vehiculoRegistrado;
	
	@Value("${parqueadero.vehiculoNoEsta}")
	private String vehiculoNoEsta;
	
	@Value("${parqueadero.vehiculoNoSoportado}")
	private String vehiculoNoSoportado;
	
	@Value("${parqueadero.vehiculoCcNoValido}")
	private String vehiculoCcNoValido;

	public String getVehiculoEnParqueadero() {
		return vehiculoEnParqueadero;
	}

	public String getVehiculoNoPuedeEntrar() {
		return vehiculoNoPuedeEntrar;
	}

	public String getParqueaderoLleno() {
		return parqueaderoLleno;
	}

	public String getVehiculoRegistrado() {
		return vehiculoRegistrado;
	}

	public String getVehiculoNoEsta() {
		return vehiculoNoEsta;
	}

	public String getVehiculoNoSoportado() {
		return vehiculoNoSoportado;
	}

	public String getVehiculoCcNoValido() {
		return vehiculoCcNoValido;
	}
	
	

}
