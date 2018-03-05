package com.whiteclarke.parking.system.exception;

/**
 * Service Exception while processing request
 * @author White Clarke
 *
 */
public class ParkingSystemException extends RuntimeException{

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 12345L;
	
	public ParkingSystemException(String errorMessage) {
		super(errorMessage);
	}
}
