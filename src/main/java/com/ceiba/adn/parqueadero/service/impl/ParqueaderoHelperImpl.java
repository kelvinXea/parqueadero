package com.ceiba.adn.parqueadero.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.configuration.ParqueaderoConfiguration;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Moto;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.service.ParqueaderoHelper;
import com.ceiba.adn.parqueadero.utils.LocalDateTimeWrapper;

@Component
public class ParqueaderoHelperImpl implements ParqueaderoHelper {

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ParqueaderoConfiguration parqueaderoConfiguration;

	@Autowired
	private LocalDateTimeWrapper localDateTimeWrapper;

	@Override
	public boolean existeVehiculoEnParqueadero(String placa) {
		return facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, placa).isPresent();
	}

	@Override
	public boolean puedeEntrarEnElParqueadero(String placa) {
		LocalDateTime ldt = localDateTimeWrapper.now();

		return placa.toLowerCase().toCharArray()[0] == 'a'
				? !(ldt.getDayOfWeek() == DayOfWeek.SUNDAY || ldt.getDayOfWeek() == DayOfWeek.MONDAY)
				: true;
	}

	@Override
	public boolean parqueaderoEstaLleno(TipoVehiculo tipoVehiculo) {
		return facturaRepository.countByTipoVehiculoAndIsCompleto(tipoVehiculo, false) == parqueaderoConfiguration
				.getMaxCantidadVehiculo(tipoVehiculo);
	}

	@Override
	public Factura generarFacturaEntrada(Vehiculo vehiculo) {

		switch (vehiculo.getTipoVehiculo()) {
		case CARRO:

			return new Factura(vehiculo.getPlaca(), localDateTimeWrapper.now(), vehiculo.getTipoVehiculo());

		case MOTO:

			return new Factura(vehiculo.getPlaca(), localDateTimeWrapper.now(), vehiculo.getTipoVehiculo(),
					vehiculo.getCc());

		default:
			// TODO agregar mensaje al property
			throw new ParqueaderoException("El tipo de este vehiculo no es soportado");
		}

	}

	@Override
	public Factura generarFacturaSalida(Factura factura) {

		factura.setFechaSalida(localDateTimeWrapper.now());

		Vehiculo vehiculo;
		switch (factura.getTipoVehiculo()) {
		case MOTO:

			vehiculo = new Moto(factura.getPlaca(), factura.getCc());
			break;

		case CARRO:

			vehiculo = new Carro(factura.getPlaca());
			break;

		default:
			// TODO
			throw new ParqueaderoException("acomodar");

		}

		float segundos = ChronoUnit.SECONDS.between(factura.getFechaEntrada(), factura.getFechaSalida());
		float horas = (segundos / 3600);
		float dias = horas / 24;
		float horasRestantes =  ((dias - (int) dias) * 24);
		float minutosRestantes = (horasRestantes - (int) horasRestantes) * 60;
		int pagoTotal = ((int) dias * vehiculo.getValorDia());

		// regla si lleva mas o igual a 10 min en el parqueadero se considera una hora a
		// pagar

		if ((int) minutosRestantes >= 10) {
			horasRestantes += 1;
		}
	
		if (horasRestantes >= 9) {
			pagoTotal = pagoTotal + vehiculo.getValorDia();
		} else {
			pagoTotal = pagoTotal + ((int) horasRestantes * vehiculo.getValorHora());
		}

		if (vehiculo.getTipoVehiculo() == TipoVehiculo.MOTO && vehiculo.getCc() > 500) {
			pagoTotal = pagoTotal + 2000;
		}

		factura.setCompleto(true);
		factura.setPagoTotal(pagoTotal);

		return factura;
	}

}
