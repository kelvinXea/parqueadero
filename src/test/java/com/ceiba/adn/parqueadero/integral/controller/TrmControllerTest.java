package com.ceiba.adn.parqueadero.integral.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.ceiba.adn.parqueadero.ParqueaderoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TrmControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void consultarTrmTest() throws Exception {
		// arrange
		// act
		// assert
		mvc.perform(get("/trm").contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
