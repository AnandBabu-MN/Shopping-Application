package com.shopping.application.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationResponse {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String email;
    private String address;
    private long phoneNumber;
}
