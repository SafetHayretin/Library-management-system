package com.safetKyuchyukhalil.libraryManagementSystem.DTOs.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Address;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Role;
import jakarta.persistence.*;

public class MemberResponse {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private boolean isStatusActive;

    private AddressResponse address;

    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatusActive() {
        return isStatusActive;
    }

    public void setStatusActive(boolean statusActive) {
        isStatusActive = statusActive;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
