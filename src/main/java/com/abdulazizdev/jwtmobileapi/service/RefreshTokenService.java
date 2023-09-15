package com.abdulazizdev.jwtmobileapi.service;

import com.abdulazizdev.jwtmobileapi.dto.TokenRefreshResponse;
import com.abdulazizdev.jwtmobileapi.model.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Long userId);
    void deleteByUserId(Long userId);
    TokenRefreshResponse doRefresh(String refreshToken);
}
