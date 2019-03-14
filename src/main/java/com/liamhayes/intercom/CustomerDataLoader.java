package com.liamhayes.intercom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDataLoader {
	
	private static Logger logger = LoggerFactory.getLogger(CustomerDataLoader.class);
	
	private static final String NAME = "name";
	private static final String USER_ID = "user_id";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * Loads a JSON file and converts contents to Customer objects which are then added to a List
	 * @param file text file with JSON customer data
	 * @return list of Customer objects sorted by user id ascending order
	 */
    public static List<Customer> readFile(String file) {
    	List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            	Customer aCustomer = processLine(line);
            	if (aCustomer != null) {
            		customers.add(aCustomer);
            	}
            }
        } catch (IOException e) {
        	logger.error("Invalid file path provided, could not load \"{}\". Exception: {}", file, e.getMessage());
        }
        Collections.sort(customers);
        return customers;
    }
    
    protected static Customer processLine(String line) {
    	Customer aCustomer = null;
    	if (validInputFormat(line)) {
			try {
				aCustomer = OBJECT_MAPPER.readValue(line, Customer.class);
			} catch (IOException e) {
				logger.error("Invalid JSON format for line: {}", line);
			}
    	} else {
    		logger.warn("Line will not be mapped to a Customer object. Invalid customer format: {}", line);
    	}
        return aCustomer;
    }
    
    private static boolean validInputFormat(String line) {
    	return line.contains(NAME) && line.contains(USER_ID) 
    			&& line.contains(LATITUDE) && line.contains(LONGITUDE);
    }
}