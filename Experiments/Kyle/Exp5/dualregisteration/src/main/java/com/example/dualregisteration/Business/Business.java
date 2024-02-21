package com.example.dualregisteration.Business;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Business {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    // this should be unique, so we can use it as a key
    
    @Column(unique = true)
    private String businessName;

    private String address;
    private int zipcode;
    private String city;

    public Business(String businessName, String address, int zipcode, String city) {
        this.businessName = businessName;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Business() {

    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
