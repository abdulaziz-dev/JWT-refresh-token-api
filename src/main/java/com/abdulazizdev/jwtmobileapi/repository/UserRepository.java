package com.abdulazizdev.jwtmobileapi.repository;

import com.abdulazizdev.jwtmobileapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
