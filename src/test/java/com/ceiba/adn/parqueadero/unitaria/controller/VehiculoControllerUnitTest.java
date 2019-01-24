package com.ceiba.adn.parqueadero.unitaria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ceiba.adn.parqueadero.ParqueaderoApplication;
import com.ceiba.adn.parqueadero.builder.VehiculoTestDataBuilder;
import com.ceiba.adn.parqueadero.domain.model.dto.VehiculoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoApplication.class)
@AutoConfigureMockMvc
public class VehiculoControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void registrarVehiculoExceptionTest() throws Exception {
		// arrange
		VehiculoTestDataBuilder vtdb = new VehiculoTestDataBuilder().withfechaEntrada(LocalDateTime.now())
				.withTipoVehiculo(null);
		VehiculoDTO vehiculoDto = vtdb.buildVehiculoDTO();
		// act
		ResultActions ra = mvc.perform(post("/vehiculo").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(vehiculoDto)).contentType(MediaType.APPLICATION_JSON));
		// assert
		ra.andExpect(status().isBadRequest());


	}

}
