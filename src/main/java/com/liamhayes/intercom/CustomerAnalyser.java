package com.liamhayes.intercom;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerAnalyser {

	private static Logger logger = LoggerFactory.getLogger(CustomerAnalyser.class);

	private static final double MAX_DISTANCE_FROM_OFFICE = 100;
	private static final double AN_OUT_OF_RANGE_DISTANCE = 10000;

	private String file;
	private List<Customer> customers;

	public CustomerAnalyser(String file) {
		this.file = file;
	}

	public void runAnalysis() {
		customers = CustomerDataLoader.readFile(file);
		filterCustomersWithinRangeOfIntercom();
		printCustomersForPartyInvite();
		customers = new ArrayList<>();
	}

	private void filterCustomersWithinRangeOfIntercom() {
		List<Customer> filteredCustomers = new ArrayList<>();
		for (Customer aCustomer : customers) {
			double customerDistance = AN_OUT_OF_RANGE_DISTANCE;
			double latitude = Double.parseDouble(aCustomer.getLatitude());
			double longitude = Double.parseDouble(aCustomer.getLongitude());
			try {
				customerDistance = GreatCircleCalculator.calculateDistanceInKm(latitude, longitude);
			} catch (Exception e) {
				logger.error("Unable to calculate distance for customer: [{}]", aCustomer);
			}
			if (withinRange(customerDistance)) {
				filteredCustomers.add(aCustomer);
			}
		}
		customers = filteredCustomers;
	}

	private boolean withinRange(double customerDistance) {
		return customerDistance < MAX_DISTANCE_FROM_OFFICE;
	}

	private void printCustomersForPartyInvite() {
		logger.info("Customers within 100km from Intercom Office:");
		for (Customer aCustomer : customers) {
			logger.info(aCustomer.getId() + " " + aCustomer.getName());
		}
	}
}
