package com.ceiba.adn.parqueadero.integral.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ceiba.adn.parqueadero.ParqueaderoApplication;
import com.ceiba.adn.parqueadero.builder.FacturaTestDataBuilder;
import com.ceiba.adn.parqueadero.builder.VehiculoTestDataBuilder;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class VehiculoControllerIntegralTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FacturaRepository facturaVehiculoRepository;

	private final static String PLACA = "TOOEZXD";
	private final static String PLACA2 = "TOOEZXDT";
	private final static Integer CC = 100;

	@Test
	public void registrarVehiculoCarroTest() throws Exception {
		// arrange
		VehiculoTestDataBuilder vtdb = new VehiculoTestDataBuilder().withfechaEntrada(LocalDateTime.now());
		VehiculoDTO vehiculoDto = vtdb.buildVehiculoDTO();
		// act
		mvc.perform(post("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(vehiculoDto)).contentType(MediaType.APPLICATION_JSON));

		Optional<Factura> factura = facturaVehiculoRepository.findByIsCompletoAndPlacaIgnoreCase(false,
				vehiculoDto.getPlaca());
		// assert
		assertEquals(vehiculoDto.getPlaca(), factura.get().getPlaca());

	}

	@Test
	public void consultarVehiculosTest() throws Exception {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		facturaVehiculoRepository.save(ftdb.build());
		ResultActions ra = mvc.perform(get("/vehiculo").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		// assert
		ra.andExpect(status().isOk());

	}

	@Test
	public void registrarSalidaVehiculoTest() throws JsonProcessingException, Exception {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder().withPlaca(PLACA);
		Factura factura = ftdb.build();
		facturaVehiculoRepository.save(factura);

		VehiculoTestDataBuilder vtdb = new VehiculoTestDataBuilder().withPlaca(factura.getPlaca());
		// act
		mvc.perform(put("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(vtdb.buildVehiculoDTO()))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		Optional<Factura> facturaFinal = facturaVehiculoRepository.findByIsCompletoAndPlacaIgnoreCase(true,
				factura.getPlaca());
		// assert
		assert (facturaFinal.get().isCompleto());

	}

	@Test
	public void registrarVehiculoMotoTest() throws JsonProcessingException, Exception {
		// arrange
		VehiculoTestDataBuilder vtdb = new VehiculoTestDataBuilder().withfechaEntrada(LocalDateTime.now())
				.withTipoVehiculo(TipoVehiculo.MOTO).withCc(CC).withPlaca(PLACA2);
		VehiculoDTO vehiculoDto = vtdb.buildVehiculoDTO();
		// act
		mvc.perform(post("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(vehiculoDto)).contentType(MediaType.APPLICATION_JSON));

		Optional<Factura> factura = facturaVehiculoRepository.findByIsCompletoAndPlacaIgnoreCase(false,
				vehiculoDto.getPlaca());
		// arrange
		assertEquals(vehiculoDto.getPlaca(), factura.get().getPlaca());

	}

	@Test
	public void registrarSalidaExceptionHandler() throws JsonProcessingException, Exception {
		// arrange
		VehiculoTestDataBuilder vtdb = new VehiculoTestDataBuilder().withPlaca(PLACA);
		// act
		ResultActions ra = mvc.perform(put("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(vtdb.buildVehiculoDTO()))
				.contentType(MediaType.APPLICATION_JSON));
		// assert
		ra.andExpect(status().isBadRequest());
	}

}
