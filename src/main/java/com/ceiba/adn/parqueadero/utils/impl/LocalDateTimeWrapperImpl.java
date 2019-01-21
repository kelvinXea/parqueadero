package com.ceiba.adn.parqueadero.utils.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ceiba.adn.parqueadero.utils.LocalDateTimeWrapper;

@Component
public class LocalDateTimeWrapperImpl implements LocalDateTimeWrapper {

	@Override
	public LocalDateTime now() {
		return LocalDateTime.now();
	}

}
