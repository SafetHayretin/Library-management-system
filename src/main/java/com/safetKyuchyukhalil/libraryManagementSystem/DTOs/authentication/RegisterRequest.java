package com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.users.AddressRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Address;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String username;

    private AddressRequest address;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member toEntity() {
        Member member = new Member();

        member.setFirstName(getFirstName());
        member.setLastName(getLastName());
        member.setEmail(getEmail());
        member.setAddress(getAddress().toEntity());
        member.setRole(Role.MEMBER);
        member.setUsername(getUsername());
        member.setPassword(passwordEncoder.encode(getPassword()));
        member.setPhoneNumber(getPhoneNumber());
        member.setStatusActive(true);

        return member;
    }
}
