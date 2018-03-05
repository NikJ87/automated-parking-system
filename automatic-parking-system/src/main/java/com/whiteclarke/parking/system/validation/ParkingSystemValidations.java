package com.whiteclarke.parking.system.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whiteclarke.parking.system.constants.ParkingSystemConstants;
import com.whiteclarke.parking.system.exception.ParkingSystemException;
import com.whiteclarke.parking.system.exception.ParkingSystemValidationException;

/**
 * Validation class which validates for various conditions and throw Validation Exception
 * @author White Clarke
 *
 */
public class ParkingSystemValidations {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingSystemValidations.class);
	
	private ParkingSystemValidations() {
		// empty constructor
	}
	
	public static void validateParkingSystemRequest(String input) {
		nullOrEmptyRequest(input);
		validateInputFormat(input);
	}
	
	private static void nullOrEmptyRequest(String input) {
		
		if ( StringUtils.isBlank(input) ) {
			LOGGER.error("Request must not be empty or null");
			throw new ParkingSystemValidationException(ValidationCode.NULLREQ);
		}
		
	}
	
	private static void validateInputFormat(String input) {
		
		String[] inputs = input.split(",");
	    
	    validateRequestFormat(inputs);

	    String[] coordinatesStr = inputs[0].split(":");
	    validateXYNumber(coordinatesStr);
	    validateXYMinMax(coordinatesStr);
	    validateCommandSequence(inputs[1]);
	}

	/**
	 * Validate the Request format should be defining in <<x:y>,<command sequence>>
	 *  		where command sequence should be string e.g. RFLFFFRF
	 * @param inputs
	 * @param coordinatesStr
	 */
	private static void validateRequestFormat(String[] inputs) {
		if ( inputs.length != 2 || StringUtils.isBlank(inputs[0])  
	    		|| StringUtils.isBlank(inputs[1])  ) {
			LOGGER.error("Coordinates or Command Seq shouldn't be null or empty");
	    	throw new ParkingSystemValidationException(ValidationCode.FORMAT);
	    }
		
		String[] coordinatesStr = inputs[0].split(":");
	    if ( coordinatesStr.length != 2 || StringUtils.isBlank(coordinatesStr[0])  
	    		|| StringUtils.isBlank(coordinatesStr[1])  ) {
	    	LOGGER.error("Coordinates mustn't be empty or null");
	    	throw new ParkingSystemValidationException(ValidationCode.FORMAT);
	    }
	}
	
	
	/**
	 * Check that XY coordinate should be number only
	 * @param coordinatesStr
	 */
	private static void validateXYNumber(String[] coordinatesStr) {
		if ( !StringUtils.isNumeric(coordinatesStr[0]) 
				|| !StringUtils.isNumeric(coordinatesStr[1]) ) {
			LOGGER.error("Coordinates must be number");
			throw new ParkingSystemValidationException(ValidationCode.XYNUM);
		}
		
	}
	
	
	/**
	 * Check X and Y coordinates should be within Range 
	 * Min = 1
	 * Max = defined in constants
	 * @param coordinatesStr
	 */
	private static void validateXYMinMax(String[] coordinatesStr) {
		
		int x = Integer.parseInt(coordinatesStr[0]);
		int y = Integer.parseInt(coordinatesStr[1]);
		
		if ( x < 1 || y < 1 ) {
			LOGGER.error("X or Y must be greater or equal to 1");
			throw new ParkingSystemValidationException(ValidationCode.XYMIN);
		}
		
		if ( validateXAxisCoordinate(x, ParkingSystemConstants.X_AXIS) ) {
			LOGGER.error("X coordinate must not exceed the  Rows range");
			throw new ParkingSystemValidationException("X coordinate shouldn't exceed the range of " + ParkingSystemConstants.X_COORDINATE_RANGE, ValidationCode.XMAX);
		}
		
		if ( validateYAxisCoordinate(y, ParkingSystemConstants.Y_AXIS) ) {
			LOGGER.error("Y coordinate must not exceed the  Column range");
			throw new ParkingSystemValidationException("Y coordinate shouldn't exceed the range of " + ParkingSystemConstants.Y_COORDINATE_RANGE, ValidationCode.YMAX);
		}
		
	}
	
	private static void validateCommandSequence(String commandSequence) {
		if ( !commandSequence.matches("[RLFrlf]*" ) ) {
			LOGGER.error("Command sequence can have only R, L or F");
			throw new ParkingSystemValidationException("Command Sequence can have only R , L, F" , ValidationCode.COMMAND);
		}
	}
	
	/**
	 * Command sequence is incorrect, if coordinate goes out of Range
	 * @param coordinate
	 * @return
	 * @throws Exception
	 */
	public static  int validateNewCoordinate(int coordinate, char axis) {
		if ( coordinate <= 0 
				|| ParkingSystemValidations.validateXAxisCoordinate(coordinate, axis) 
				|| ParkingSystemValidations.validateYAxisCoordinate(coordinate, axis) ) {
			LOGGER.error("Boundary reached with intermediate Coordinate - {} and Axis - {}", coordinate, axis);
			throw new ParkingSystemException("Command Sequence resulted in invalid coordinates");
		}
		return coordinate;
	}
	
	
	/**
	 * Validate X-Axis coordinate range defined in constants
	 * @param coordinate
	 * @param axis
	 * @return
	 */
	private static  boolean validateXAxisCoordinate(int coordinate, char axis) {
		return ParkingSystemConstants.X_AXIS == axis && coordinate > ParkingSystemConstants.X_COORDINATE_RANGE;
	}
	
	/**
	 * Validate Y-Axis coordinate range defined in constants
	 * @param coordinate
	 * @param axis
	 * @return
	 */
	private static  boolean validateYAxisCoordinate(int coordinate, char axis) {
		return ParkingSystemConstants.Y_AXIS == axis && coordinate > ParkingSystemConstants.Y_COORDINATE_RANGE;
	}
	
}
