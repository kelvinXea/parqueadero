package com.ceiba.adn.parqueadero.serviceprovider.trmservice.controller;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;

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

	private static final  String DATE_RESPONSE_FORMAT = "#0.00";

	private static final  String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";

	@GetMapping("/trm")
	public ResponseEntity<TrmDTO> consultarTrm() {
		DecimalFormat decimalFormat = new DecimalFormat(DATE_RESPONSE_FORMAT);

		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
		TrmDTO trmdto = new TrmDTO();

		try {

			TcrmResponse tcrmResponse = proxy.queryTCRM(null);

			trmdto.setVigenciaInicio(Instant.ofEpochMilli(tcrmResponse.getValidityFrom().getTimeInMillis())
					.atZone(ZoneId.systemDefault()).toLocalDateTime());
			trmdto.setVigenciaFin(Instant.ofEpochMilli(tcrmResponse.getValidityTo().getTimeInMillis())
					.atZone(ZoneId.systemDefault()).toLocalDateTime());
			trmdto.setValor(Double.parseDouble(decimalFormat.format(tcrmResponse.getValue())));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(trmdto, HttpStatus.OK);

	}

}
