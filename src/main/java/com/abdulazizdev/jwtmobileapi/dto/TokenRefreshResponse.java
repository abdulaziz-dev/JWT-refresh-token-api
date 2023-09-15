package com.abdulazizdev.jwtmobileapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRefreshResponse {
    private String newAccessToken;
    private String tokenType;
    private String refreshToken;

    public TokenRefreshResponse(String newAccessToken, String refreshToken){
        this.newAccessToken = newAccessToken;
        this.refreshToken = refreshToken;
        this.tokenType = "Bearer";
    }

}
