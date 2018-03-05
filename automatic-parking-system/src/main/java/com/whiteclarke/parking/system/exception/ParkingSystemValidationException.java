package com.whiteclarke.parking.system.exception;

import com.whiteclarke.parking.system.validation.ValidationCode;

/**
 * Service Exception for validation
 * @author White Clarke
 *
 */
public class ParkingSystemValidationException extends RuntimeException{

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 1234L;
	
	private ValidationCode errorCode;
	
	public ParkingSystemValidationException(String errorMessage, ValidationCode errorCode) {
		super(errorMessage);
		this.errorCode = errorCode;
	}
	
	public ParkingSystemValidationException(ValidationCode errorCode) {
		super(errorCode.getValue());
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public ValidationCode getErrorCode() {
		return errorCode;
	}

}
