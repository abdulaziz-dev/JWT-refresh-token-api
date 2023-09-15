package com.abdulazizdev.jwtmobileapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private String refreshToken;


}
