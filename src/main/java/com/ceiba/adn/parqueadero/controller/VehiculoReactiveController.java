package com.ceiba.adn.parqueadero.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;
import com.ceiba.adn.parqueadero.domain.service.ParqueaderoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
@RequestMapping("/rx/vehiculo")
public class VehiculoReactiveController {
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("")
	public Flux<VehiculoDTO> consultarVehiculos() {
		return parqueaderoService.obtenerVehiculosEnElParqueaderoReactive();		
	}
	
	@PostMapping("")
	public Mono<String> registrarVehiculo(@RequestBody VehiculoDTO vehiculo) {
		return parqueaderoService.registrarIngresoVehiculoReactive(vehiculo).map(v -> v.getPlaca());
	}
	
}
