package com.ceiba.adn.parqueadero.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.parqueadero.model.entity.Factura;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long>{

	List<Factura> findByIsCompleto(Boolean isCompleto);
	
	
}
