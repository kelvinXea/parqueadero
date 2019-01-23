package com.ceiba.adn.parqueadero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import com.ceiba.adn.parqueadero.controller.VehiculoController;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;
import com.ceiba.adn.parqueadero.serviceprovider.trmservice.controller.TrmController;

@SpringBootApplication
public class ParqueaderoApplication {

	@Autowired
	FacturaRepository facturaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
		
			
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {

	    
	}

}

