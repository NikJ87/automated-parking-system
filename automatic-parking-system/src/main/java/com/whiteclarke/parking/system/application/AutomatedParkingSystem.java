package com.whiteclarke.parking.system.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whiteclarke.parking.system.request.ParkingSystemRequest;
import com.whiteclarke.parking.system.service.ParkingSystemService;
import com.whiteclarke.parking.system.transformer.ParkingSystemTransformer;

public class AutomatedParkingSystem {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutomatedParkingSystem.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ParkingSystemRequest request = ParkingSystemTransformer.createParkingRequest("5:5,RFLFRFLF");
		int[] finalCoordinates = ParkingSystemService.getFinalCoordinates(request);

		LOGGER.debug("Final Coordinate Generated : {}", finalCoordinates);
		
	}
	
}
