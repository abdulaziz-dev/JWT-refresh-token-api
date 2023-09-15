package com.abdulazizdev.jwtmobileapi.controller;

import com.abdulazizdev.jwtmobileapi.dto.*;
import com.abdulazizdev.jwtmobileapi.service.AuthService;
import com.abdulazizdev.jwtmobileapi.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        TokenRefreshResponse response = new TokenRefreshResponse();
        response.setRefreshToken(requestRefreshToken);

        return ResponseEntity.ok(refreshTokenService.doRefresh(requestRefreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        if (authService.logout()){
            return ResponseEntity.ok("Logged out successfully.");
        } else {
            return ResponseEntity.badRequest().body("Seems like you are not logged in");
        }
    }


}
