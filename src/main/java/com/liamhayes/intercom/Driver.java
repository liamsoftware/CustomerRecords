package com.liamhayes.intercom;

public class Driver {

	public static void main(String[] args) {
		String customerFileLocaiton = "./src/main/resources/customers.txt";
		CustomerAnalyser customerAnalyser = new CustomerAnalyser(customerFileLocaiton);
		customerAnalyser.runAnalysis();
	}
}
