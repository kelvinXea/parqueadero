package com.ceiba.adn.parqueadero.domain.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
import com.ceiba.adn.parqueadero.controller.WebSocketController;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Moto;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.SocketObjectResponse;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.SocketObjectAction;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.domain.service.ParqueaderoHelper;
import com.ceiba.adn.parqueadero.domain.service.ParqueaderoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class ParqueaderoServiceImpl implements ParqueaderoService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ParqueaderoHelper parqueaderoHelper;

	@Autowired
	private MensajeConfiguration mensajeConfiguration;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private WebSocketController webSocketController;

	@Autowired
	@Qualifier("jdbcScheduler")
	private Scheduler jdbcScheduler;

	@Override
	public synchronized Factura registrarIngresoVehiculo(Vehiculo vehiculo) {

		if (parqueaderoHelper.existeVehiculoEnParqueadero(vehiculo.getPlaca()))
			throw new ParqueaderoException(mensajeConfiguration.getVehiculoEnParqueadero());

		if (!parqueaderoHelper.puedeEntrarEnElParqueadero(vehiculo.getPlaca()))
			throw new ParqueaderoException(mensajeConfiguration.getVehiculoNoPuedeEntrar());

		if (parqueaderoHelper.parqueaderoEstaLleno(vehiculo.getTipoVehiculo()))
			throw new ParqueaderoException(mensajeConfiguration.getParqueaderoLleno());

		Factura factura = parqueaderoHelper.generarFacturaEntrada(vehiculo);
		Factura rFactura = facturaRepository.save(factura);
		webSocketController.enviarMensajeOnChangeVehiculoClientes(
				new SocketObjectResponse<Factura>(SocketObjectAction.ADD, rFactura));
		return rFactura;
	}

	@Override
	public List<Factura> obtenerVehiculosEnElParqueadero() {
		return facturaRepository.findByIsCompleto(false);
	}

	@Override
	public Factura registrarSalidaDeVehiculo(String placa) {
		Factura factura = facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, placa)
				.orElseThrow(() -> new ParqueaderoException(mensajeConfiguration.getVehiculoNoEsta()));

		factura = parqueaderoHelper.generarFacturaSalida(factura);

		facturaRepository.save(factura);
		webSocketController.enviarMensajeOnChangeVehiculoClientes(
		         new SocketObjectResponse<Factura>(SocketObjectAction.DELETE, factura));
		return factura;
	}

	@Override
	public Flux<VehiculoDTO> obtenerVehiculosEnElParqueaderoReactive() {
		Flux<VehiculoDTO> defer = Flux
				.defer(() -> Flux.fromIterable(toVehiculoDTO(this.facturaRepository.findByIsCompleto(false))));
		return defer.subscribeOn(jdbcScheduler);
	}

	private List<VehiculoDTO> toVehiculoDTO(List<Factura> facturas) {
		java.lang.reflect.Type listType = new TypeToken<List<VehiculoDTO>>() {
		}.getType();
		return modelMapper.map(facturas, listType);
	}

	@Override
	public Mono<Factura> registrarIngresoVehiculoReactive(VehiculoDTO vehiculoDTO) {
		return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
			Vehiculo vehiculo;
			if (vehiculoDTO.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
				vehiculo = modelMapper.map(vehiculoDTO, Moto.class);
			} else {
				vehiculo = modelMapper.map(vehiculoDTO, Carro.class);
			}

			return registrarIngresoVehiculo(vehiculo);
		})).subscribeOn(jdbcScheduler);

	}

}
