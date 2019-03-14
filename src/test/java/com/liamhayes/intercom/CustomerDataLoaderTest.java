package com.liamhayes.intercom;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;

public class CustomerDataLoaderTest {

	private static final String VALID_TEST_CUSTOMER_FILE = "./src/main/resources/customers.txt";
	private static final String NON_EXISTANT_FILE = "/TMP/DOES/NOT/EXIST/customers.txt";
	private static final String MALFORMED_JSON = "awerweacdsacdsfew";
	private static final String VALID_JSON = "{\"latitude\": \"51.999447\", \"user_id\": 14, \"name\": \"Helen Cahill\", \"longitude\": \"-9.742744\"}";
	private static final String VALID_INPUT_BUT_MALFORMED_JSON = "\"latitude\" \"51.999447\" \"user_id 14, \"name\": \"Helen Cahill, \"longitude: \"-9.742744\"}";

	@Test
	public void givenValidFile_whenLoadFile_thenPopulatedListIsCreated() {
		List<Customer> customers = CustomerDataLoader.readFile(VALID_TEST_CUSTOMER_FILE);
		assertTrue(customers.size() > 0);
	}

	@Test
	public void givenNonExistentFile_whenLoadFile_thenEmptyListIsCreated() {
		List<Customer> customers = CustomerDataLoader.readFile(NON_EXISTANT_FILE);
		assertTrue(customers.isEmpty());
	}

	@Test
	public void givenValidJSON_whenProcessLine_thenCustomerObjectRetuned() {
		Customer aCustomer = CustomerDataLoader.processLine(VALID_JSON);
		assertTrue(aCustomer != null);
	}

	@Test
	public void givenInvalidInputAndMalformedJSON_whenProcessLine_thenNullObjectReturned() {
		Customer aCustomer = CustomerDataLoader.processLine(MALFORMED_JSON);
		assertTrue(aCustomer == null);
	}

	@Test
	public void givenValidInputButMalformedJSON_whenProcessLine_thenNullObjectReturned() {
		Customer aCustomer = CustomerDataLoader.processLine(VALID_INPUT_BUT_MALFORMED_JSON);
		assertTrue(aCustomer == null);
	}
}
