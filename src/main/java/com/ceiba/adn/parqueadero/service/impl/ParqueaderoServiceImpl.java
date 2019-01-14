package com.ceiba.adn.parqueadero.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.service.ParqueaderoHelper;
import com.ceiba.adn.parqueadero.service.ParqueaderoService;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ParqueaderoHelper parqueaderoHelper;

	@Autowired
	private MensajeConfiguration mensajeConfiguration;

	@Override
	public void registrarIngresoVehiculo(Vehiculo vehiculo) {

		if (parqueaderoHelper.ExisteVehiculoEnParqueadero(vehiculo.getPlaca()))
			throw new ParqueaderoException(mensajeConfiguration.getVehiculoEnParqueadero());

		if (!parqueaderoHelper.puedeEntrarEnElParqueadero(vehiculo.getPlaca()))
			throw new ParqueaderoException(mensajeConfiguration.getVehiculoNoPuedeEntrar());

		if (parqueaderoHelper.parqueaderoEstaLleno(vehiculo.getTipoVehiculo()))
			throw new ParqueaderoException(mensajeConfiguration.getParqueaderoLleno());

		Factura factura = vehiculo.getTipoVehiculo() == TipoVehiculo.MOTO
				? new Factura(vehiculo.getPlaca(), new Date(), vehiculo.getTipoVehiculo(), vehiculo.getCc())
				: new Factura(vehiculo.getPlaca(), new Date(), vehiculo.getTipoVehiculo());

		facturaRepository.save(factura);
	}

	@Override
	public List<Factura> obtenerVehiculosEnElParqueadero() {
		return facturaRepository.findByIsCompleto(false);
	}

	@Override
	public Factura registrarSalidaDeVehiculo(String placa) {
		Factura factura = facturaRepository.findByIsCompletoAndPlaca(false, placa);
		
		factura = parqueaderoHelper.generarFacturaSalida(factura);
		
		facturaRepository.save(factura);
		
		return factura;
	}

}
