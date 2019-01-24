package com.ceiba.adn.parqueadero.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
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

	@Autowired
	private MensajeConfiguration mensajeConfiguration;

	@Override
	public boolean existeVehiculoEnParqueadero(String placa) {
		return facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, placa).isPresent();
	}

	@Override
	public boolean puedeEntrarEnElParqueadero(String placa) {
		LocalDateTime ldt = localDateTimeWrapper.now();

		return !(placa.toLowerCase().toCharArray()[0] == 'a'
				&& (ldt.getDayOfWeek() == DayOfWeek.SUNDAY || ldt.getDayOfWeek() == DayOfWeek.MONDAY));
	}

	@Override
	public boolean parqueaderoEstaLleno(TipoVehiculo tipoVehiculo) {
		return facturaRepository.countByTipoVehiculoAndIsCompleto(tipoVehiculo, false)
				.equals(parqueaderoConfiguration.getMaxCantidadVehiculo(tipoVehiculo));
	}

	@Override
	public Factura generarFacturaEntrada(Vehiculo vehiculo) {

		if (!Optional.ofNullable(vehiculo.getTipoVehiculo()).isPresent()) {

			throw new ParqueaderoException(mensajeConfiguration.getVehiculoNoSoportado());
		}

		if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
			return new Factura(vehiculo.getPlaca(), localDateTimeWrapper.now(), vehiculo.getTipoVehiculo(),
					vehiculo.getCc());
		} else {
			return new Factura(vehiculo.getPlaca(), localDateTimeWrapper.now(), vehiculo.getTipoVehiculo());
		}

	}

	@Override
	public Factura generarFacturaSalida(Factura factura) {

		factura.setFechaSalida(localDateTimeWrapper.now());

		Vehiculo vehiculo;

		if (!Optional.ofNullable(factura.getTipoVehiculo()).isPresent()) {

			throw new ParqueaderoException(mensajeConfiguration.getVehiculoNoSoportado());
		}

		if (factura.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
			vehiculo = new Moto(factura.getPlaca(), factura.getCc());
		} else {
			vehiculo = new Carro(factura.getPlaca());
		}

		float segundos = ChronoUnit.SECONDS.between(factura.getFechaEntrada(), factura.getFechaSalida());
		float horas = (segundos / 3600);
		float dias = horas / 24;
		float horasRestantes = ((dias - (int) dias) * 24);
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
