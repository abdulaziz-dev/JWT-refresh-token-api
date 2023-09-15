package com.abdulazizdev.jwtmobileapi.repository;

import com.abdulazizdev.jwtmobileapi.model.RefreshToken;
import com.abdulazizdev.jwtmobileapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    void deleteByUser(UserEntity user);
}
