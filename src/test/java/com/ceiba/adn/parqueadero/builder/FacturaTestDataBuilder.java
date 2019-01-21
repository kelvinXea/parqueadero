package com.ceiba.adn.parqueadero.builder;

import java.time.LocalDateTime;

import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.dto.FacturaDTO;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

public class FacturaTestDataBuilder {
	
	private static final String PLACA = "ASDASD";
	
	private static final LocalDateTime FECHA_ENTRADA = LocalDateTime.now();
	
	private static final LocalDateTime FECHA_SALIDA = null;
	
	private static final Integer CC = 0;
	
	private static final Integer PAGO_TOTAL = null;
	
	private static final TipoVehiculo TIPO_VEHICULO = TipoVehiculo.CARRO;
	
	private static final boolean IS_COMPLETO = false;
	
	
	private String placa;
	
	private LocalDateTime fechaEntrada;
	
	private LocalDateTime fechaSalida;
	
	private Integer cc;
	
	private Integer pagoTotal;
	
	private TipoVehiculo tipoVehiculo;
	
	private boolean isCompleto; 

	public FacturaTestDataBuilder() {
		this.placa = PLACA;
		this.fechaEntrada = FECHA_ENTRADA;
		this.fechaSalida = FECHA_SALIDA;
		this.cc = CC;
		this.pagoTotal = PAGO_TOTAL;
		this.tipoVehiculo = TIPO_VEHICULO;
		this.isCompleto = IS_COMPLETO;
	}
	
	public FacturaTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public FacturaTestDataBuilder withfechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}
	
	public FacturaTestDataBuilder withfechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public FacturaTestDataBuilder withCc(Integer cc) {
		this.cc = cc;
		return this;
	}
	
	public FacturaTestDataBuilder withPagoTotal(Integer pagoTotal) {
		this.pagoTotal = pagoTotal;
		return this;
	}
	
	public FacturaTestDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public FacturaTestDataBuilder withIsCompleto(boolean isCompleto) {
		this.isCompleto = isCompleto;
		return this;
	}
	
	public Factura build() {
		Factura factura = new Factura(this.placa, this.fechaEntrada, this.tipoVehiculo);
		factura.setCc(this.cc);
		factura.setCompleto(this.isCompleto);
		factura.setFechaSalida(this.fechaSalida);
		factura.setPagoTotal(this.pagoTotal);
		return factura;
	}
	
	public FacturaDTO buildDTO() {
		FacturaDTO facturaDto = new FacturaDTO();
		facturaDto.setCc(this.cc);
		facturaDto.setFechaEntrada(this.fechaEntrada);
		facturaDto.setFechaSalida(this.fechaSalida);
		facturaDto.setPagoTotal(this.pagoTotal);
		facturaDto.setTipoVehiculo(this.tipoVehiculo);
		facturaDto.setPlaca(this.placa);
		
		return facturaDto;
	}
	
	

}
