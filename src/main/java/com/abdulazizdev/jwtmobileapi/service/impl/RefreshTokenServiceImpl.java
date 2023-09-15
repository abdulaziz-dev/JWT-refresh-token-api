package com.abdulazizdev.jwtmobileapi.service.impl;

import com.abdulazizdev.jwtmobileapi.dto.TokenRefreshResponse;
import com.abdulazizdev.jwtmobileapi.exception.TokenRefreshException;
import com.abdulazizdev.jwtmobileapi.model.RefreshToken;
import com.abdulazizdev.jwtmobileapi.repository.RefreshTokenRepository;
import com.abdulazizdev.jwtmobileapi.repository.UserRepository;
import com.abdulazizdev.jwtmobileapi.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtServiceImpl jwtService;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

    @Override
    public TokenRefreshResponse doRefresh(String refreshToken) {
        return findByToken(refreshToken)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user);
                    return new TokenRefreshResponse(token, refreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(refreshToken,
                        "Refresh token is not in database!"));
    }
}
