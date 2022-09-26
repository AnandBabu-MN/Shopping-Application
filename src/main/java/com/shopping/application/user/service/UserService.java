package com.shopping.application.user.service;

import com.shopping.application.user.dto.RegistrationResponse;
import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface UserService {
    List<User> getAllUsers();
    Boolean validateUserCredentials(UserDto UserDto);
    ResponseEntity<RegistrationResponse> saveUser(UserDto UserDto);
    ResponseEntity<UserDto> updateUser(UserDto UserDto);
    ResponseEntity deleteUser(long id);
    User getUserById(long id);

}
