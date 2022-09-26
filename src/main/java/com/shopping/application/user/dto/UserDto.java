package com.shopping.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String password;
    private String dob;
    private String gender;
    private String email;
    private String address;
    private long phoneNumber;
    private boolean isAdmin;
}
