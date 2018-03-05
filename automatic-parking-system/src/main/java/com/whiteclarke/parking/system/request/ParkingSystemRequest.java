package com.whiteclarke.parking.system.request;

/**
 * Class which creates request from string input
 * @author White Clarke
 *
 */
public class ParkingSystemRequest {
	
	/**
	 * X and Y coordintes represents Row and Column respectively.
	 * Separated by : in the input request
	 */
	private int[] coordinates;
	
	/**
	 * Command Sequence in the input request string
	 */
	private char[] commandSequence;

	/**
	 * @return the coordinates
	 */
	public int[] getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the commandSequence
	 */
	public char[] getCommandSequence() {
		return commandSequence;
	}

	/**
	 * @param commandSequence the commandSequence to set
	 */
	public void setCommandSequence(char[] commandSequence) {
		this.commandSequence = commandSequence;
	}
	
}
