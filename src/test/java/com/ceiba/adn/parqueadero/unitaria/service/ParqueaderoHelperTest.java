package com.ceiba.adn.parqueadero.unitaria.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Moto;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.service.ParqueaderoHelper;
import com.ceiba.adn.parqueadero.service.impl.ParqueaderoHelperImpl;
import com.ceiba.adn.parqueadero.utils.LocalDateTimeWrapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoHelperTest {

	@TestConfiguration
	static class ParqueaderoServiceImplTestContextConfiguration {

		@Bean
		public ParqueaderoHelper parqueaderoHelper() {
			return new ParqueaderoHelperImpl();
		}

	}

	@MockBean
	private LocalDateTimeWrapper localDateTimeWrapper;

	@MockBean
	private FacturaRepository facturaRepository;

	@Autowired
	private ParqueaderoHelper parqueaderoHelper;

	private static final LocalDateTime DIA_LUNES = LocalDateTime.of(2019, Month.JANUARY, 21, 7, 0);
	private static final LocalDateTime DIA_DOMINGO = LocalDateTime.of(2019, Month.JANUARY, 20, 7, 0);
	private static final LocalDateTime DIA_MARTES = LocalDateTime.of(2019, Month.JANUARY, 22, 7, 0);
	private static final LocalDateTime HORA_1PM = LocalDateTime.of(2019, Month.JANUARY, 22, 13, 0);
	private static final LocalDateTime HORA_130PM = LocalDateTime.of(2019, Month.JANUARY, 22, 13, 30);
	private static final LocalDateTime HORA_6AM = LocalDateTime.of(2019, Month.JANUARY, 22, 6, 0);
	private static final LocalDateTime HORA_4AM = LocalDateTime.of(2019, Month.JANUARY, 22, 4, 0);
	private static final String PLACA_CON_LETRA_A = "ASDASD";
	private static final String PLACA_SIN_LETRA_A = "EUWEWE";
	private static final int MIN_CC = 49;
	private static final int MAX_CANTIDAD_CARROS = 20;
	private static final int MAX_CANTIDAD_MOTOS = 10;
	private static final int MIN_CANTIDAD_CARROS = 0;
	private static final int MIN_CANTIDAD_MOTOS = 0;
	private static final int VALOR_1DIA_CARRO = 8000;
	private static final int VALOR_7HORAS_CARRO = 7000;
	private static final int VALOR_1DIA_MOTO = 4000;
	private static final int VALOR_7HORAS_MOTO = 3500;
	private static final int VALOR_PAGO_CC_MOTO = 2000;

	@Test
	public void noPuedeEntrarEnElParqueaderoLunesConLetraATest() {
		// arrange
		// act
		when(localDateTimeWrapper.now()).thenReturn(DIA_LUNES);
		// assert
		assertFalse(parqueaderoHelper.puedeEntrarEnElParqueadero(PLACA_CON_LETRA_A));
	}

	@Test
	public void noPuedeEntrarEnElParqueaderoDomingoConLetraATest() {
		// arrange
		// act
		when(localDateTimeWrapper.now()).thenReturn(DIA_DOMINGO);
		// assert
		assertFalse(parqueaderoHelper.puedeEntrarEnElParqueadero(PLACA_CON_LETRA_A));
	}

	@Test
	public void puedeEntrarEnElParqueaderoLunesConLetraATest() {
		// arrange
		// act
		when(localDateTimeWrapper.now()).thenReturn(DIA_LUNES);
		// assert
		assert (parqueaderoHelper.puedeEntrarEnElParqueadero(PLACA_SIN_LETRA_A));
	}

	@Test
	public void puedeEntrarEnElParqueaderoDomingoConLetraATest() {
		// arrange
		// act
		when(localDateTimeWrapper.now()).thenReturn(DIA_DOMINGO);
		// assert
		assert (parqueaderoHelper.puedeEntrarEnElParqueadero(PLACA_SIN_LETRA_A));
	}

	@Test
	public void puedeEntrarEnElParqueaderoOtroDiaConLetraATest() {
		// arrange
		// act
		when(localDateTimeWrapper.now()).thenReturn(DIA_MARTES);
		// assert
		assert (parqueaderoHelper.puedeEntrarEnElParqueadero(PLACA_CON_LETRA_A));
	}

	@Test
	public void elVehiculoSeEncuentraEnElParqueaderoTest() {
		// arrange
		Factura factura = new FacturaTestDataBuilder().withPlaca(PLACA_CON_LETRA_A).withIsCompleto(false).build();
		// act
		when(facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, PLACA_CON_LETRA_A))
				.thenReturn(Optional.of(factura));
		// assert
		assert (parqueaderoHelper.existeVehiculoEnParqueadero(PLACA_CON_LETRA_A));
	}

	@Test
	public void elVehiculoNoSeEncuentraEnElParqueaderoTest() {
		// arrange
		// act
		when(facturaRepository.findByIsCompletoAndPlacaIgnoreCase(false, PLACA_CON_LETRA_A))
				.thenReturn(Optional.ofNullable(null));
		// assert
		assertFalse(parqueaderoHelper.existeVehiculoEnParqueadero(PLACA_CON_LETRA_A));
	}

	@Test
	public void generarVehiculoCarroTest() {
		// arrange
		Vehiculo vehiculo = new Carro(PLACA_SIN_LETRA_A);
		// act
		Factura factura = parqueaderoHelper.generarFacturaEntrada(vehiculo);
		// assert
		assertEquals(TipoVehiculo.CARRO, factura.getTipoVehiculo());
	}

	@Test
	public void generarVehiculoMotoTest() {
		// arrange
		Vehiculo vehiculo = new Moto(PLACA_SIN_LETRA_A, MIN_CC);
		// act
		Factura factura = parqueaderoHelper.generarFacturaEntrada(vehiculo);
		// assert
		assertEquals(TipoVehiculo.MOTO, factura.getTipoVehiculo());
	}

	@Test
	public void elParqueaderoEstaLlenoMotoTest() {
		// arrange
		TipoVehiculo tipoMoto = TipoVehiculo.MOTO;
		// act
		when(facturaRepository.countByTipoVehiculoAndIsCompleto(tipoMoto, false)).thenReturn(MAX_CANTIDAD_MOTOS);
		// assert
		assert (parqueaderoHelper.parqueaderoEstaLleno(tipoMoto));
	}

	@Test
	public void elParqueaderoEstaLlenoCarroTest() {
		// arrange
		TipoVehiculo tipoCarro = TipoVehiculo.CARRO;
		// act
		when(facturaRepository.countByTipoVehiculoAndIsCompleto(tipoCarro, false)).thenReturn(MAX_CANTIDAD_CARROS);
		// assert
		assert (parqueaderoHelper.parqueaderoEstaLleno(tipoCarro));
	}

	@Test
	public void elParqueaderoNoEstaLlenoMotoTest() {
		// arrange
		TipoVehiculo tipoMoto = TipoVehiculo.MOTO;
		// act
		when(facturaRepository.countByTipoVehiculoAndIsCompleto(tipoMoto, false)).thenReturn(MIN_CANTIDAD_MOTOS);
		// assert
		assertFalse(parqueaderoHelper.parqueaderoEstaLleno(tipoMoto));
	}

	@Test
	public void elParqueaderoNoEstaLlenoCarroTest() {
		// arrange
		TipoVehiculo tipoCarro = TipoVehiculo.CARRO;
		// act
		when(facturaRepository.countByTipoVehiculoAndIsCompleto(tipoCarro, false)).thenReturn(MIN_CANTIDAD_CARROS);
		// assert
		assertFalse(parqueaderoHelper.parqueaderoEstaLleno(tipoCarro));
	}

	@Test
	public void costoCarro9HorasTest() {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		when(localDateTimeWrapper.now()).thenReturn(HORA_1PM);
		Factura factura = ftdb.withTipoVehiculo(TipoVehiculo.CARRO).withfechaEntrada(HORA_4AM).build();
		// assert
		assertEquals(VALOR_1DIA_CARRO, parqueaderoHelper.generarFacturaSalida(factura).getPagoTotal().intValue());
	}

	@Test
	public void costoCarro7HorasTest() {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		when(localDateTimeWrapper.now()).thenReturn(HORA_1PM);
		Factura factura = ftdb.withTipoVehiculo(TipoVehiculo.CARRO).withfechaEntrada(HORA_6AM).build();
		// assert
		assertEquals(VALOR_7HORAS_CARRO, parqueaderoHelper.generarFacturaSalida(factura).getPagoTotal().intValue());
	}

	@Test
	public void costoMoto9HorasTest() {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		when(localDateTimeWrapper.now()).thenReturn(HORA_1PM);
		Factura factura = ftdb.withTipoVehiculo(TipoVehiculo.MOTO).withCc(50).withfechaEntrada(HORA_4AM).build();
		// assert
		assertEquals(VALOR_1DIA_MOTO, parqueaderoHelper.generarFacturaSalida(factura).getPagoTotal().intValue());
	}

	@Test
	public void costoMoto7HorasTest() {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		when(localDateTimeWrapper.now()).thenReturn(HORA_1PM);
		Factura factura = ftdb.withTipoVehiculo(TipoVehiculo.MOTO).withCc(50).withfechaEntrada(HORA_6AM).build();
		// assert
		assertEquals(VALOR_7HORAS_MOTO, parqueaderoHelper.generarFacturaSalida(factura).getPagoTotal().intValue());
	}

	@Test
	public void costoMoto7HorasCon600CcTest() {
		// arrange
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		when(localDateTimeWrapper.now()).thenReturn(HORA_1PM);
		Factura factura = ftdb.withTipoVehiculo(TipoVehiculo.MOTO).withCc(600).withfechaEntrada(HORA_6AM).build();
		// assert
		assertEquals(VALOR_7HORAS_MOTO + VALOR_PAGO_CC_MOTO,
				parqueaderoHelper.generarFacturaSalida(factura).getPagoTotal().intValue());
	}

	@Test
	public void costoMoto7HorasCon30MinCon600CcTest() {
		// arrange
		Moto moto = new Moto();
		FacturaTestDataBuilder ftdb = new FacturaTestDataBuilder();
		// act
		when(localDateTimeWrapper.now()).thenReturn(HORA_130PM);
		Factura factura = ftdb.withTipoVehiculo(TipoVehiculo.MOTO).withCc(600).withfechaEntrada(HORA_6AM).build();
		// assert
		assertEquals(VALOR_7HORAS_MOTO + VALOR_PAGO_CC_MOTO + moto.getValorHora(),
				parqueaderoHelper.generarFacturaSalida(factura).getPagoTotal().intValue());
	}
}
