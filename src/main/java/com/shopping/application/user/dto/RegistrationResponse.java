package com.shopping.application.user.dto;

import com.shopping.application.common.entity.UserProduct;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationResponse {
    private long userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String email;
    private String address;
    private long phoneNumber;
    private boolean isAdmin;
}
