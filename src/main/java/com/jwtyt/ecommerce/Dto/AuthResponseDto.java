package com.jwtyt.ecommerce.Dto;

import com.jwtyt.ecommerce.Models.Role;
import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String TokenType="Bearer";
    private List<Role> roles;
    public AuthResponseDto(String accessToken , List<Role> roles){
        this.accessToken=accessToken;
        this.roles=roles;
    }

}
