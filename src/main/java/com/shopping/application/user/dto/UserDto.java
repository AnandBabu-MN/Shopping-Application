package com.shopping.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private long userId;
    @NotBlank(message = "FirstName cannot be Blank")
    @NotNull(message = "FirstName cannot be Null")
    private String firstName;

    @NotBlank(message = "Last Name cannot be Blank")
    @NotNull(message = "FirstName cannot be Null")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be Blank")
    private String password;

    @NotBlank(message = "Date of Birth Must be needed")
    private String dob;

    @NotNull(message = "Gender Type is Required")
    @NotBlank(message = "Gender Type is Required")
    private String gender;

    @Email(message = "Email Invalid")
    private String email;

    @NotNull(message = "Address is mandatory for placing an order")
    @NotBlank(message = "Address is mandatory for placing an order")
    private String address;

    @Pattern(regexp = "^\\d{10}$",message = "Invalid Phone Number")
    private String phoneNumber;
    private boolean isAdmin;
}
