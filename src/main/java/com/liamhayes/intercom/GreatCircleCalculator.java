package com.liamhayes.intercom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreatCircleCalculator {

	private static Logger logger = LoggerFactory.getLogger(GreatCircleCalculator.class);

	public static final double INTERCOM_LATITUDE = 53.339428;
	public static final double INTERCOM_LONGITUDE = -6.257664;

	private static final double EARTH_RADIUS_IN_KM = 6371;
	private static final double MAX_LATITUDE = 180.0;
	private static final double MIN_LATITUDE = -180.0;
	private static final double MAX_LONGITUDE = 90.0;
	private static final double MIN_LONGITUDE = -90.0;

	/**
	 * Calculates the great circle distance in KM of the provided location to that
	 * of the Intercom Dublin office Uses the Haversine formula detailed on
	 * https://en.wikipedia.org/wiki/Great-circle_distance
	 * 
	 * @param customerLatitude
	 * @param customerLongitude
	 * @return the distance in km of the provided coordinates from the Intercom
	 *         Dublin office
	 * @throws Exception
	 *             if the provided coordinates are out of bounds for latitude or
	 *             longitude
	 */
	public static double calculateDistanceInKm(double customerLatitude, double customerLongitude) throws Exception {
		if (!isValidLatitude(customerLatitude) || !isValidLongitude(customerLongitude)) {
			throw new Exception(
					"Invalid Coordinates provided. LAT[" + customerLatitude + "], LON[" + customerLongitude + "]");
		}
		return performHaversineCalculation(customerLatitude, customerLongitude);
	}

	private static boolean isValidLatitude(double latitude) {
		if (latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE) {
			return true;
		}
		logger.warn("Lattitude [{}] coodinate is out of bounds", latitude);
		return false;
	}

	private static boolean isValidLongitude(double longitude) {
		if (longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE) {
			return true;
		}
		logger.warn("Longitude [{}] coodinate is out of bounds", longitude);
		return false;
	}

	private static double performHaversineCalculation(double customerLatitude, double customerLongitude) {
		double intercomLatitudeRadian = Math.toRadians(INTERCOM_LATITUDE);
		double customerLatitudeRadian = Math.toRadians(customerLatitude);
		double latitudeDeltaRadian = Math.toRadians((customerLatitude - INTERCOM_LATITUDE));
		double longitudeDeltaRadian = Math.toRadians((customerLongitude - INTERCOM_LONGITUDE));

		double a = Math.pow(Math.sin(latitudeDeltaRadian / 2), 2) + Math.cos(intercomLatitudeRadian)
				* Math.cos(customerLatitudeRadian) * Math.pow(Math.sin(longitudeDeltaRadian / 2), 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (EARTH_RADIUS_IN_KM * c);
	}
}
