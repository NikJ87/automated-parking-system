package com.whiteclarke.parking.system.validation;

/**
 * Enum for maintaining the required error codes used for validation exception
 * @author White Clarke
 *
 */
public enum ValidationCode {

	XYMIN("X-Y Coordinate minimum value is 1"),
	XYNUM("X-Y Coordinate should be number"),
	XMAX("X-Coordinate maximum value exceeded"),
	YMAX("Y-Coordinate maximum value exceeded"),
	COMMAND("Valid Commands are : 'R' , 'L', 'F"),
	NULLREQ("Request can't be null or blank"),
	FORMAT("Invalid Request Format. Required Format - 'X:Y,RFRFRFFF' ");
	
	
	private String value;
	
	/**
	 * Constructor
	 */
	private ValidationCode(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
}
