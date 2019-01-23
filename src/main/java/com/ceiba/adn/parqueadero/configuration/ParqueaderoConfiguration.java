package com.ceiba.adn.parqueadero.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

@Configuration
@PropertySource("classpath:parqueadero.properties")
public class ParqueaderoConfiguration {

	@Value("${maxCantidadCarros}")
	private Integer maxCantidadCarros;

	@Value("${maxCantidadMotos}")
	private Integer maxCantidadMotos;

	public Integer getMaxCantidadVehiculo(TipoVehiculo tipoVehiculo) {
		switch (tipoVehiculo) {
		case CARRO:

			return maxCantidadCarros;

		case MOTO:

			return maxCantidadMotos;

		default:

			return null;
		}
	}

}
