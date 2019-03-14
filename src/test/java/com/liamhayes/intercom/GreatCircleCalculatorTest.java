package com.liamhayes.intercom;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GreatCircleCalculatorTest {

	private static final double OUT_OF_BOUNDS_LATITUDE = 2000;
	private static final double OUT_OF_BOUNDS_LONGITUDE = -2000;

	@Test
	public void givenValidIntercomCoordinates_whenCalculateDistance_thenDistanceCalculatedCorrectlyAsZero()
			throws Exception {
		double distanceToIntercomOffice = GreatCircleCalculator.calculateDistanceInKm(
				GreatCircleCalculator.INTERCOM_LATITUDE, GreatCircleCalculator.INTERCOM_LONGITUDE);
		assertTrue(0.0 == distanceToIntercomOffice);
	}

	@Test(expected = Exception.class)
	public void givenValidIntercomCoordinates_whenCalculateDistanceForOutOfBoundsLatitude_thenExceptionThrown()
			throws Exception {
		GreatCircleCalculator.calculateDistanceInKm(OUT_OF_BOUNDS_LATITUDE, GreatCircleCalculator.INTERCOM_LONGITUDE);
	}

	@Test(expected = Exception.class)
	public void givenValidIntercomCoordinates_whenCalculateDistanceForOutOfBoundsLonogitude_thenExceptionThrown()
			throws Exception {
		GreatCircleCalculator.calculateDistanceInKm(GreatCircleCalculator.INTERCOM_LATITUDE, OUT_OF_BOUNDS_LONGITUDE);
	}
}
