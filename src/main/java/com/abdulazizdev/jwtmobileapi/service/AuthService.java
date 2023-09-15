package com.abdulazizdev.jwtmobileapi.service;

import com.abdulazizdev.jwtmobileapi.dto.JwtAuthResponse;
import com.abdulazizdev.jwtmobileapi.dto.LoginDto;
import com.abdulazizdev.jwtmobileapi.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto request);
    JwtAuthResponse login(LoginDto request);

    boolean logout();
}
