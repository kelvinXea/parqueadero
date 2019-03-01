package com.ceiba.adn.parqueadero.domain.model.dto;

import com.ceiba.adn.parqueadero.domain.model.dto.enums.SocketObjectAction;

public class SocketObjectResponse<T> {
	
	private SocketObjectAction action;
	
	private T object;

	public SocketObjectResponse(SocketObjectAction action, T object) {
		this.action = action;
		this.object = object;
	}

	public SocketObjectAction getAction() {
		return action;
	}

	public T getObject() {
		return object;
	}
	
	

}
