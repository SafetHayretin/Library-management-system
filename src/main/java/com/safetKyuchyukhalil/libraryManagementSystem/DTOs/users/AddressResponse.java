package com.safetKyuchyukhalil.libraryManagementSystem.DTOs.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Address;

public class AddressResponse {
    private String city;

    private String country;

    private String street;

    private String streetNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
