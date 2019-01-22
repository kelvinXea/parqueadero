package com.ceiba.adn.parqueadero.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.adn.parqueadero.domain.entity.Factura;
import com.ceiba.adn.parqueadero.domain.model.dto.enums.TipoVehiculo;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long>{

	Optional<Factura> findByIsCompletoAndPlacaIgnoreCase(Boolean isCompleto, String placa);
	Integer countByTipoVehiculoAndIsCompleto(TipoVehiculo tipoVehiculo, Boolean isCompleto);
	List<Factura> findByIsCompleto(Boolean isCompleto);
	
	
}
