package com.liamhayes.intercom;


import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO to represent the customer objects
 * Implements Comparable allowing sorting of Customers by user id in ascending order
 */
public class Customer implements Comparable<Customer> {

	@JsonProperty("user_id")
    private int id;
    private String name;
    private String longitude;
    private String latitude;

    public Customer() {
    }
    
    public Customer(int id, String name, String longitude, String latitude) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String toString() {
        return "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude;
    }

	@Override
	public int compareTo(Customer otherCustomer) {
		return this.id - otherCustomer.getId();
	}
}