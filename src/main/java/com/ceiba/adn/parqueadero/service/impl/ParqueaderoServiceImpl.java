package com.ceiba.adn.parqueadero.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
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
	public synchronized Factura registrarIngresoVehiculo(Vehiculo vehiculo) {

		if (parqueaderoHelper.existeVehiculoEnParqueadero(vehiculo.getPlaca()))
			throw new ParqueaderoException(mensajeConfiguration.getVehiculoEnParqueadero());

		if (!parqueaderoHelper.puedeEntrarEnElParqueadero(vehiculo.getPlaca()))
			throw new ParqueaderoException(mensajeConfiguration.getVehiculoNoPuedeEntrar());

		if (parqueaderoHelper.parqueaderoEstaLleno(vehiculo.getTipoVehiculo()))
			throw new ParqueaderoException(mensajeConfiguration.getParqueaderoLleno());
		
		Factura factura = parqueaderoHelper.generarFacturaEntrada(vehiculo);
				
		return facturaRepository.save(factura);
	}

	@Override
	public List<Factura> obtenerVehiculosEnElParqueadero() {
		return facturaRepository.findByIsCompleto(false);
	}

	@Override
	public Factura registrarSalidaDeVehiculo(String placa) {
		Factura factura = facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, placa)
				//TODO agregar a los mensajes
				.orElseThrow(() -> new ParqueaderoException("El vehiculo no se encuentra en el parqueadero"));
				
		
		factura = parqueaderoHelper.generarFacturaSalida(factura);
		
		facturaRepository.save(factura);
		
		return factura;
	}

}
