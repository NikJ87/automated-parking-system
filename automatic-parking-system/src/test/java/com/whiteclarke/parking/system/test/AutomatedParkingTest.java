package com.whiteclarke.parking.system.test;

import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whiteclarke.parking.system.exception.ParkingSystemException;
import com.whiteclarke.parking.system.exception.ParkingSystemValidationException;
import com.whiteclarke.parking.system.request.ParkingSystemRequest;
import com.whiteclarke.parking.system.service.ParkingSystemService;
import com.whiteclarke.parking.system.transformer.ParkingSystemTransformer;
import com.whiteclarke.parking.system.validation.ValidationCode;

/**
 * Test class running through various cases
 * @author White Clarke
 *
 */
public class AutomatedParkingTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutomatedParkingTest.class);
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void parkingRequestEmpty() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.NULLREQ)));
		
		LOGGER.debug("Testing request string as empty");
		ParkingSystemTransformer.createParkingRequest("");
		
	}
	
	@Test
	public void parkingRequestNull() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.NULLREQ)));
		
		LOGGER.debug("Testing request string as null");
		ParkingSystemTransformer.createParkingRequest(null);
		
	}
	
	@Test
	public void parkingRequestEmptyCommandSequence() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.FORMAT)));
		
		LOGGER.debug("Testing request string with empty command sequence");
		ParkingSystemTransformer.createParkingRequest("5:5,");
		
	}
	
	@Test
	public void parkingRequestEmptyCoordinates() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.FORMAT)));
		
		LOGGER.debug("Testing request string without coordinates");
		ParkingSystemTransformer.createParkingRequest(",RFLFRFF");
		
	}
	
	@Test
	public void parkingRequestEmptyCoordinatesAndCommandSequence() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.FORMAT)));
		
		LOGGER.debug("Testing request string with empty command sequence and without coordinates");
		ParkingSystemTransformer.createParkingRequest(",");
		
	}
	
	@Test
	public void parkingXCoordinateEmpty() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.FORMAT)));
		
		LOGGER.debug("Testing request string with no X coordinate");
		ParkingSystemTransformer.createParkingRequest(":4,RFLFFF");
		
	}
	
	@Test
	public void parkingYCoordinateEmpty() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.FORMAT)));
		
		LOGGER.debug("Testing request string with no Y coordinate");
		ParkingSystemTransformer.createParkingRequest("4:,RFLFFF");
		
	}
	
	@Test
	public void parkingYCoordinateSpace() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.FORMAT)));
		
		LOGGER.debug("Testing request string with Y coordinate as Space");
		ParkingSystemTransformer.createParkingRequest("4: ,RFLFFF");
		
	}
	
	@Test
	public void parkingXCoordinateAsAlpha() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XYNUM)));
		
		LOGGER.debug("Testing request string with X coordinate as non-number");
		ParkingSystemTransformer.createParkingRequest("X:4,RFLFFF");
		
	}
	
	@Test
	public void parkingYCoordinateAsSpecialCharacter() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XYNUM)));
		
		LOGGER.debug("Testing request string with Y coordinate as special character");
		ParkingSystemTransformer.createParkingRequest("Y:@,RFLFFF");
		
	}
	
	@Test
	public void parkingYCoordinateAsNegative() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XYNUM)));
		
		LOGGER.debug("Testing request string with Y coordinate as negative");
		ParkingSystemTransformer.createParkingRequest("4:-4,RFLFFF");
		
	}
	
	@Test
	public void parkingXCoordinateAsZero() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XYMIN)));
		
		LOGGER.debug("Testing request string with X coordinate as zero");
		ParkingSystemTransformer.createParkingRequest("0:4,RFLFFF");
		
	}
	
	@Test
	public void parkingYCoordinateAsZero() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XYMIN)));
		
		LOGGER.debug("Testing request string with Y coordinate as zero");
		ParkingSystemTransformer.createParkingRequest("7:0,RFLFFF");
		
	}
	
	@Test
	public void parkingXYCoordinateAsZero() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XYMIN)));
		
		LOGGER.debug("Testing request string with X and Y coordinate as zero");
		ParkingSystemTransformer.createParkingRequest("0:0,RFLFFF");
		
	}
	
	@Test
	public void parkingXCoordinateMoreThanXRange() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.XMAX)));
		
		LOGGER.debug("Testing request string with X coordinate more tha range as 15");
		ParkingSystemTransformer.createParkingRequest("16:4,RFLFFF");
		
	}
	
	@Test
	public void parkingYCoordinateMoreThanYRange() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.YMAX)));
		
		LOGGER.debug("Testing request string with Y coordinate more tha range as 15");
		ParkingSystemTransformer.createParkingRequest("15:17,RFLFFF");
		
	}
	
	@Test
	public void commandSequenceWithInvalidCommand() {
		
		thrown.expect(ParkingSystemValidationException.class);
		thrown.expect(Matchers.<ParkingSystemValidationException>hasProperty("errorCode", Matchers.equalTo(ValidationCode.COMMAND)));
		
		LOGGER.debug("Testing request string with invalid command character");
		ParkingSystemTransformer.createParkingRequest("15:15,RFLdFFF");
		
	}
	
	@Test
	public void commandGeneratesCoordinatesLessThanMin() {
		
		thrown.expect(ParkingSystemException.class);
		thrown.expectMessage("Command Sequence resulted in invalid coordinates");
		
		LOGGER.debug("Testing request string with command sequence taking coordinate below minimum");
		callService("1:1,LFRF");
		
	}
	
	@Test
	public void commandGeneratesCoordinatesBeyondMax() {
		
		thrown.expect(ParkingSystemException.class);
		thrown.expectMessage("Command Sequence resulted in invalid coordinates");
		
		LOGGER.debug("Testing request string with command sequence taking coordinate beyond max range");
		callService("15:1,FFF");
		
	}
	
	@Test
	public void testLeftTurnDirectionWithInvalidDirection() {
		
		thrown.expect(ParkingSystemException.class);
		thrown.expectMessage("Inavlid left command turn");
		
		ParkingSystemService.getLeftTurnDirection('T');
		
	}
	
	@Test
	public void testRightTurnDirectionWithInvalidDirection() {
		
		thrown.expect(ParkingSystemException.class);
		thrown.expectMessage("Inavlid right command turn");
		
		ParkingSystemService.getRightTurnDirection('T');
		
	}
	
	@Test
	public void testGetDirectionWithInvalidDirection() {
		
		thrown.expect(ParkingSystemException.class);
		thrown.expectMessage("Inavlid command turn");
		
		ParkingSystemService.getDirection('D', 'T');
		
	}
	
	@Test
	public void testForwardCommandWithInvalidDirection() {
		
		thrown.expect(ParkingSystemException.class);
		thrown.expectMessage("Inavlid direction for forward command turn");
		
		ParkingSystemService.forwardCommand('D', new int[] {4, 4});
		
	}
	
	@Test
	public void providedScenario1() {
		
		LOGGER.debug("Testing request string with valid scenario");
		int[] finalCoordinates = callService("5:5,RFLFRFLF");
		
		assertTrue("Final Coordinates should match", finalCoordinates[0] == 7 && finalCoordinates[1] == 7);
	}
	
	@Test
	public void providedScenario2() {
		
		LOGGER.debug("Testing request string with valid scenario");
		int[] finalCoordinates = callService("6:6,FFLFFLFFLFF");
		
		assertTrue("Final Coordinates should match", finalCoordinates[0] == 6 && finalCoordinates[1] == 6);
	}
	
	@Test
	public void providedScenario3() {
		
		LOGGER.debug("Testing request string with valid scenario");
		int[] finalCoordinates = callService("5:5,FLFLFFRFFF");
		
		assertTrue("Final Coordinates should match", finalCoordinates[0] == 4 && finalCoordinates[1] == 1);
	}
	
	@Test
	public void validScenario() {
		
		LOGGER.debug("Testing request string with valid scenario");
		int[] finalCoordinates = callService("5:5,RFRFRFRF");
		
		assertTrue("Final Coordinates should match", finalCoordinates[0] == 5 && finalCoordinates[1] == 5);
	}
	
	
	/**
	 * Service call to create the request, validate  and get final coordinates
	 * @param input
	 */
	private int[] callService(String input) {
		ParkingSystemRequest request = ParkingSystemTransformer.createParkingRequest(input);
		int[] finalCoordinates = ParkingSystemService.getFinalCoordinates(request);
		
		LOGGER.debug("Final Parking Coordinates : {}", finalCoordinates);
		
		return finalCoordinates;
	}
}
