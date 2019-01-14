package com.ceiba.adn.parqueadero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.ceiba.adn.parqueadero.configuration.MensajeConfiguration;
import com.ceiba.adn.parqueadero.domain.exception.ParqueaderoException;
import com.ceiba.adn.parqueadero.domain.repository.FacturaRepository;

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

