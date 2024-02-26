package com.club.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String userName;
    String password;
    String firstName;
    String lastName;
    String email;
}
