package com.shopping.application.user.controller;

import com.shopping.application.user.dto.RegistrationResponse;
import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import com.shopping.application.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        log.info("UserController || getAllUsers || getting the User Details ");
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam long id){
        log.info("UserController || getUserById || getting User by ID");
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<RegistrationResponse> saveUser(@RequestBody UserDto userDto){
        log.info("UserController || saveUser || Saving Users to DB");
        return userService.saveUser(userDto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        log.info("UserController || updateUser || Updating a User");
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam long id){
        log.info("UserController || deleteUser || Deleting a User");
        return userService.deleteUser(id);
    }

}
