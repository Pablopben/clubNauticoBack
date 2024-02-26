package com.club.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserDTO {

    private String userName;
    private String email;
    private String firstName;
    private String lastName;

}
