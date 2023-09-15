package com.abdulazizdev.jwtmobileapi.dto;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
