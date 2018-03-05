package com.whiteclarke.parking.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whiteclarke.parking.system.constants.ParkingSystemConstants;
import com.whiteclarke.parking.system.exception.ParkingSystemException;
import com.whiteclarke.parking.system.request.ParkingSystemRequest;
import com.whiteclarke.parking.system.validation.ParkingSystemValidations;

/**
 * Service which process the Parking Request created to generate final coordinat,
 * by running through each command from a sequence
 * @author White Clarke
 *
 */
public class ParkingSystemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingSystemService.class);

	private ParkingSystemService() {
		// empty constructor
	}

	public static int[] getFinalCoordinates(ParkingSystemRequest request) {
		int[] finalCordinates = request.getCoordinates();
		char[] commandSequence = request.getCommandSequence();
		char direction = 'N';

		for ( char nextCommand : commandSequence ) {
			if ( 'L' == nextCommand || 'R' == nextCommand ) {
				direction = getDirection(direction, nextCommand);
				LOGGER.debug("Next Direction : {}", direction);
			} else if ( 'F' == nextCommand ) {
				forwardCommand(direction, finalCordinates);
				LOGGER.debug("Next Cordinates : {}", finalCordinates);
			}
		}
		LOGGER.debug("final coordinates to be returned: {}", finalCordinates);
		return finalCordinates;
	}

	/**
	 * Change in 'X' or 'Y' coordinates based on direction on forward command
	 * 
	 * @param direction
	 * @param coordinates
	 * @throws Exception
	 */
	public static void forwardCommand(char direction, int[] coordinates) {

		switch ( direction ) {
		case 'N':
			coordinates[0] = ParkingSystemValidations.validateNewCoordinate(coordinates[0] + 1,
					ParkingSystemConstants.X_AXIS);
			break;
		case 'S':
			coordinates[0] = ParkingSystemValidations.validateNewCoordinate(coordinates[0] - 1,
					ParkingSystemConstants.X_AXIS);
			break;
		case 'E':
			coordinates[1] = ParkingSystemValidations.validateNewCoordinate(coordinates[1] + 1,
					ParkingSystemConstants.Y_AXIS);
			break;
		case 'W':
			coordinates[1] = ParkingSystemValidations.validateNewCoordinate(coordinates[1] - 1,
					ParkingSystemConstants.Y_AXIS);
			break;
		default:
			throw new ParkingSystemException("Inavlid direction for forward command turn");
		}

	}

	/**
	 * Change in direction when taking Left or Right turn
	 * 
	 * @param direction
	 * @param turn
	 * @return
	 * @throws Exception
	 */
	public static char getDirection(char direction, char turn) {

		switch ( turn ) {
		case 'L':
			return getLeftTurnDirection(direction);
		case 'R':
			return getRightTurnDirection(direction);
		default:
			throw new ParkingSystemException("Inavlid command turn");
		}

	}

	/**
	 * Change in direction from current direction when takes one RIGHT turn
	 * 
	 * @param direction
	 * @return
	 * @throws Exception
	 */
	public static char getRightTurnDirection(char direction) {
		switch ( direction ) {
		case 'N':
			return 'E';
		case 'E':
			return 'S';
		case 'S':
			return 'W';
		case 'W':
			return 'N';
		default:
			throw new ParkingSystemException("Inavlid right command turn");
		}
	}

	/**
	 * Change in direction from current direction when takes one LEFT turn
	 * 
	 * @param direction
	 * @return
	 * @throws Exception
	 */
	public static char getLeftTurnDirection(char direction) {
		switch ( direction ) {
		case 'N':
			return 'W';
		case 'W':
			return 'S';
		case 'S':
			return 'E';
		case 'E':
			return 'N';
		default:
			throw new ParkingSystemException("Inavlid left command turn");
		}
	}

}
