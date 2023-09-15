package com.abdulazizdev.jwtmobileapi.service.impl;

import com.abdulazizdev.jwtmobileapi.model.RefreshToken;
import com.abdulazizdev.jwtmobileapi.model.RoleEnum;
import com.abdulazizdev.jwtmobileapi.model.UserEntity;
import com.abdulazizdev.jwtmobileapi.repository.UserRepository;
import com.abdulazizdev.jwtmobileapi.service.AuthService;
import com.abdulazizdev.jwtmobileapi.service.JwtService;
import com.abdulazizdev.jwtmobileapi.dto.JwtAuthResponse;
import com.abdulazizdev.jwtmobileapi.dto.LoginDto;
import com.abdulazizdev.jwtmobileapi.dto.RegisterDto;
import com.abdulazizdev.jwtmobileapi.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Override
    public String register(RegisterDto request) {
        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode("pass"))
                .role(RoleEnum.USER)
                .build();
        userRepository.save(user);
//        String jwt = jwtService.generateToken(user);
//        return JwtAuthResponse.builder().token(jwt).build();
        return "User registered successfully!";
    }

    @Override
    public JwtAuthResponse login(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), "pass")
        );
        UserEntity user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("Invalid phone number"));
        String jwt = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return JwtAuthResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @Override
    public boolean logout() {
        try{
            UserEntity principal = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            refreshTokenService.deleteByUserId(principal.getId());
            return true;
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
