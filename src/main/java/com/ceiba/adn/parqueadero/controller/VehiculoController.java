package com.ceiba.adn.parqueadero.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Moto;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.FacturaDTO;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.service.ParqueaderoService;

//@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
@CrossOrigin()
@RestController
public class VehiculoController {

	@Autowired
	private ParqueaderoService parqueaderoService;

	@Autowired
	private MensajeConfiguration mensajeConfiguration;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/vehiculo")
	public ResponseEntity<String> registrarVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
		
		if (!Optional.ofNullable(vehiculoDTO.getTipoVehiculo()).isPresent()) {

			throw new ParqueaderoException(mensajeConfiguration.getVehiculoNoSoportado());
		}
		Vehiculo vehiculo;

		if (vehiculoDTO.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
			vehiculo = modelMapper.map(vehiculoDTO, Moto.class);
		} else {
			vehiculo = modelMapper.map(vehiculoDTO, Carro.class);
		}
		
		parqueaderoService.registrarIngresoVehiculo(vehiculo);
		return ResponseEntity.ok().body('"' + mensajeConfiguration.getVehiculoRegistrado() + '"');
	}

	@GetMapping("/vehiculo")
	public ResponseEntity<List<VehiculoDTO>> consultarVehiculos() {
		java.lang.reflect.Type listType = new TypeToken<List<VehiculoDTO>>() {
		}.getType();
		List<VehiculoDTO> lista = modelMapper.map(parqueaderoService.obtenerVehiculosEnElParqueadero(), listType);

		return new ResponseEntity<>(lista, HttpStatus.OK);

	}

	@PutMapping("/vehiculo")
	public ResponseEntity<FacturaDTO> registrarSalidaVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
		FacturaDTO facturaDto = modelMapper.map(parqueaderoService.registrarSalidaDeVehiculo(vehiculoDTO.getPlaca()),
				FacturaDTO.class);
		return new ResponseEntity<>(facturaDto, HttpStatus.OK);

	}

}
