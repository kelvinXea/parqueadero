package com.ceiba.adn.parqueadero.serviceprovider.trmservice.controller;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.parqueadero.serviceprovider.trmservice.dto.TrmDTO;
import com.ceiba.adn.parqueadero.serviceprovider.trmservice.response.TcrmResponse;
import com.ceiba.adn.parqueadero.serviceprovider.trmservice.service.TCRMServicesInterfaceProxy;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
@RestController
public class TrmController {

	private final static String _DATE_RESPONSE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";

	// private final static String _DATE_QUERY_FORMAT = "yyyy-MM-dd";
	private final static String _VALUE_QUERY_FORMAT = "#0.00";

	private final static String _WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";

	@GetMapping("/trm")
	public ResponseEntity<TrmDTO> consultarTrm() {
		// Simple date format declaration

		//
		// Decimal value format declaration
		DecimalFormat decimalFormat = new DecimalFormat(_VALUE_QUERY_FORMAT);

		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(_WEB_SERVICE_URL);
		TrmDTO trmdto = new TrmDTO();
		//
		// Gets the TCRM value for the current date
		try {

			TcrmResponse tcrmResponse = proxy.queryTCRM(null);

			trmdto.setVigenciaInicio(Instant.ofEpochMilli(tcrmResponse.getValidityFrom().getTimeInMillis())
					.atZone(ZoneId.systemDefault()).toLocalDateTime());
			trmdto.setVigenciaFin(Instant.ofEpochMilli(tcrmResponse.getValidityTo().getTimeInMillis())
					.atZone(ZoneId.systemDefault()).toLocalDateTime());
			trmdto.setValor(Double.parseDouble(decimalFormat.format(tcrmResponse.getValue())));

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<TrmDTO>(trmdto, HttpStatus.OK);

	}

}
