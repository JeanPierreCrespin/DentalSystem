package com.dental.system.entities.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    public String username;
    public String password;
}
