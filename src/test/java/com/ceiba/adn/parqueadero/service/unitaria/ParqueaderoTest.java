package com.ceiba.adn.parqueadero.service.unitaria;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.ceiba.adn.parqueadero.service.impl.ParqueaderoHelperImpl;

public class ParqueaderoTest {
	
	
	ParqueaderoHelperImpl parqueaderoHelper;
	
	@Test
	public void noPuedeEntrarEnElParqueaderoTest() {
		
		parqueaderoHelper = new ParqueaderoHelperImpl();
		
		assertFalse(parqueaderoHelper.puedeEntrarEnElParqueadero("ASDASD"));
	}
	
	@Test
	public void puedeEntrarEnElParqueaderoTest() {
		
		parqueaderoHelper = new ParqueaderoHelperImpl();
		
		assert(parqueaderoHelper.puedeEntrarEnElParqueadero("SSDASD"));
	}
	
	

}
