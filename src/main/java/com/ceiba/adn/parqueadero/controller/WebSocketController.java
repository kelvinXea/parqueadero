package com.ceiba.adn.parqueadero.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.ceiba.adn.parqueadero.domain.model.dto.SocketObjectResponse;

@Controller
public class WebSocketController {
	
    @Autowired
    private SimpMessagingTemplate template;
	

	@MessageMapping("/hello")
	@SendTo("/vehiculo/change")
    public String enviarMensajeOnChangeVehiculo(String placa) {
    	return placa;
    }
	
	
    public void enviarMensajeOnChangeVehiculoClientes(SocketObjectResponse<?> message) {
    	this.template.convertAndSend("/vehiculo/change", message);
    }

}
