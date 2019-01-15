package com.ceiba.adn.parqueadero.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.configuration.ParqueaderoConfiguration;
import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.model.Carro;
import com.ceiba.adn.parqueadero.domain.model.Moto;
import com.ceiba.adn.parqueadero.domain.model.Vehiculo;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.service.ParqueaderoHelper;

import net.bytebuddy.implementation.bytecode.Throw;

@Component
public class ParqueaderoHelperImpl implements ParqueaderoHelper {
	
	@Autowired
	FacturaRepository facturaRepository;
	
	@Autowired
	ParqueaderoConfiguration parqueaderoConfiguration;

	@Override
	public boolean ExisteVehiculoEnParqueadero(String placa) {
		return facturaRepository.findByIsCompletoAndPlaca(false, placa) != null;
	}

	@Override
	public boolean puedeEntrarEnElParqueadero(String placa) {
		Calendar fechaHoy = Calendar.getInstance(); 
		fechaHoy.setTime(new Date());
		return placa.toLowerCase().toCharArray()[0] == 'a' ? !(fechaHoy.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
				fechaHoy.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ) : true;
	}

	@Override
	public boolean parqueaderoEstaLleno(TipoVehiculo tipoVehiculo) {
		return facturaRepository.countByTipoVehiculoAndIsCompleto(tipoVehiculo, false) ==
				parqueaderoConfiguration.getMaxCantidadVehiculo(tipoVehiculo);
	}

	@Override
	public Factura generarFacturaSalida(Factura factura) {
	    String sDate1="19/01/2019 10:05:05";  
	    try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(sDate1);
			factura.setFechaSalida(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		Vehiculo vehiculo;
		switch (factura.getTipoVehiculo()) {
		case MOTO:
			
			vehiculo = new Moto(factura.getPlaca(), factura.getCc());
			break;
			
		case CARRO:
			
			vehiculo = new Carro(factura.getPlaca());
			break;
			
		default:
			
			throw new ParqueaderoException("acomodar");
			
		}
		
		float segundos = (factura.getFechaSalida().getTime() - factura.getFechaEntrada().getTime()) / 1000;
		float horas = (segundos / 3600); 
		float dias = horas / 24;
		float horasRestantes = (float) (( dias - (int)dias ) * 24);
		float minutosRestantes = (horasRestantes - (int) horasRestantes ) * 60;
		int pagoTotal =  ((int) dias * vehiculo.getValorDia());
		
		if(horasRestantes >= 9) {
			pagoTotal = pagoTotal + vehiculo.getValorDia();
		}else {
			pagoTotal = pagoTotal + ((int)horasRestantes * vehiculo.getValorHora());
		}
		
		if(vehiculo.getTipoVehiculo() == TipoVehiculo.MOTO && vehiculo.getCc() > 500) {
			pagoTotal = pagoTotal + 2000;
		}
		
		factura.setCompleto(true);
		factura.setPagoTotal(pagoTotal);
		
		
		return factura;
	}

}
