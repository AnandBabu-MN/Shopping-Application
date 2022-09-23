package com.shopping.application.user.dto;

import com.shopping.application.common.entity.UserProduct;
import com.shopping.application.user.entity.User;
import lombok.*;

import javax.persistence.*;

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
