package com.ceiba.adn.parqueadero.unitaria.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.adn.parqueadero.builder.FacturaTestDataBuilder;
import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
import com.ceiba.adn.parqueadero.configuration.ParqueaderoConfiguration;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Moto;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.service.ParqueaderoService;
import com.ceiba.adn.parqueadero.service.impl.ParqueaderoServiceImpl;
import com.ceiba.adn.parqueadero.utils.LocalDateTimeWrapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoServiceTest {
	
	@TestConfiguration
	static class ParqueaderoServiceImplTestContextConfiguration {

		@Bean
		public ParqueaderoService parqueaderoService() {
			return new ParqueaderoServiceImpl();
		}

	}
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Autowired
	private MensajeConfiguration mensajeConfiguration;
	
	@Autowired
	private ParqueaderoConfiguration parqueaderoConfiguration;
	
	@MockBean
	private LocalDateTimeWrapper localDateTimeWrapper;
	
	@MockBean
	private FacturaRepository facturaRepository;
	
	
	private static final LocalDateTime DIA_LUNES = LocalDateTime.of(2019, Month.JANUARY, 21, 7, 0);
	private static final String PLACA_CON_LETRA_A = "ASDASD";
	private static final String PLACA_SIN_LETRA_A = "EUWEWE";
	
	@Test
	public void noPuedeEntrarEnElParqueaderoExceptionTest() {
		// arrange
		Vehiculo vehiculo = new Carro(PLACA_CON_LETRA_A);
		// act
		when(localDateTimeWrapper.now()).thenReturn(DIA_LUNES);
		try {
			parqueaderoService.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (ParqueaderoException e) {
			// assert
			assertEquals(mensajeConfiguration.getVehiculoNoPuedeEntrar(), e.getMessage());
		}
		
	}
	
	@Test
	public void elVehiculoSeEncuentraEnElParqueaderoExceptionTest() {
		// arrange
		Vehiculo vehiculo = new Carro(PLACA_SIN_LETRA_A);
		Factura factura = new FacturaTestDataBuilder().withPlaca(PLACA_SIN_LETRA_A).withIsCompleto(false).build();
		// act
		when(facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, PLACA_SIN_LETRA_A)).thenReturn(Optional.of(factura));
		try {
			parqueaderoService.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (ParqueaderoException e) {
			// assert
			assertEquals(mensajeConfiguration.getVehiculoEnParqueadero(), e.getMessage());
		}
	} 
	
	@Test
	public void elParqueaderoEstaLlenoExceptionTest() {
		// arrange
		TipoVehiculo tipoMoto = TipoVehiculo.MOTO;
		Vehiculo vehiculo = new Moto(PLACA_SIN_LETRA_A, 50);
		// act
		when(facturaRepository.countByTipoVehiculoAndIsCompleto(tipoMoto, false)).thenReturn(parqueaderoConfiguration.getMaxCantidadVehiculo(tipoMoto));
		try {
			parqueaderoService.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (ParqueaderoException e) {
			// assert
			assertEquals(mensajeConfiguration.getParqueaderoLleno(), e.getMessage());
		}
	}

}
