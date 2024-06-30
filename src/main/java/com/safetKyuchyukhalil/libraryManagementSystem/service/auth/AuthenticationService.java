package com.safetKyuchyukhalil.libraryManagementSystem.service.auth;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication.AuthenticationRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication.AuthenticationResponse;
import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.authentication.RegisterRequest;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Address;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Role;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.users.AddressRepository;
import com.safetKyuchyukhalil.libraryManagementSystem.service.users.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    private final AddressRepository addressRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(MemberService memberService, PasswordEncoder passwordEncoder, AddressRepository addressRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails details = memberService.loadUserByUsername(request.getUsername());

        AuthenticationResponse response = new  AuthenticationResponse();
        response.setToken(jwtService.generateToken(details));

        return response;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        Address address = request.getAddress();
        Address savedAddress = addressRepository.save(address);
        Member member = new Member();

        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setAddress(savedAddress);
        member.setRole(Role.MEMBER);
        member.setUsername(request.getUsername());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setPhoneNumber(request.getPhoneNumber());
        member.setStatusActive(true);

        Member registeredMember = memberService.save(member);
        AuthenticationResponse response = new  AuthenticationResponse();
        response.setToken(jwtService.generateToken(registeredMember));

        return response;
    }
}
