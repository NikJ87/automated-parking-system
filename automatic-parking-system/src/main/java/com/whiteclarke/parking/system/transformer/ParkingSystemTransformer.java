package com.whiteclarke.parking.system.transformer;

import com.whiteclarke.parking.system.request.ParkingSystemRequest;
import com.whiteclarke.parking.system.validation.ParkingSystemValidations;

/**
 * Class which transforms the plain String request into the request required by Service.
 * Also call validation service to validate the input request string
 * @author White Clarke
 *
 */
public class ParkingSystemTransformer {

	private ParkingSystemTransformer() {
		// empty constructor
	}
	
	public static ParkingSystemRequest createParkingRequest(String input) {
		
		ParkingSystemRequest request = new ParkingSystemRequest();
		
		ParkingSystemValidations.validateParkingSystemRequest(input);
		
		String[] inputs = input.split(",");
	    String[] coordinatesStr = inputs[0].split(":");
	    
	    int[] coordinates = new int[] {Integer.parseInt(coordinatesStr[0]), Integer.parseInt(coordinatesStr[1])};
	    char[] commandSequence = inputs[1].toCharArray();
		
	    request.setCommandSequence(commandSequence);
	    request.setCoordinates(coordinates);
	    
		return request;
	}
}
