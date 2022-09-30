package com.shopping.application.user.serviceimpl;

import com.shopping.application.config.ErrorCodes;
import com.shopping.application.config.ResponseMessage;
import com.shopping.application.user.dto.RegistrationResponse;
import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import com.shopping.application.user.repository.UserRepository;
import com.shopping.application.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAllUsers() {
        logger.info("UserServiceImpl || getAllUsers || Get user details");
        return userRepository.findAll();

    }

    public Boolean validateUserCredentials(UserDto userDto) {
        String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if (!Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE).matcher((userDto.getEmail())).find()) {
            throw new IllegalArgumentException(ErrorCodes.EMAIL_INVALID);
        }
        String password = userDto.getPassword();
        String regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (!Pattern.compile(regexp).matcher(password).find()) {
            throw new IllegalArgumentException(ErrorCodes.PASSWORD_INVALID + "Minimum eight characters," +
                    " at least one uppercase letter, " +
                    "one lowercase letter" +
                    " and one number:");
        }
        return true;
    }


    @Override
    public ResponseEntity<RegistrationResponse> saveUser(UserDto userDto) {
        Boolean isValid = validateUserCredentials(userDto);
        if (!isValid) {
            throw new IllegalArgumentException(ErrorCodes.EMAIL_INVALID);
        }
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException(ErrorCodes.EMAIL_ALREADY_REGISTERED);
        }
        User user = modelMapper.map(userDto, User.class);
        User newUser = userRepository.save(user);
        modelMapper.map(newUser, RegistrationResponse.class);
        logger.info("UserServiceImpl || saveUser || Users saved to DB");
        return new ResponseEntity(new ResponseMessage(true, "User Registered Successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UserDto userDto) {
        userRepository.findById(userDto.getUserId()).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.USER_NOT_FOUND));
        User user = modelMapper.map(userDto, User.class);
        User updatedUser = userRepository.save(user);
        modelMapper.map(updatedUser, UserDto.class);
        logger.info("UserServiceImpl || updateUser || User detail has been updated");
        return new ResponseEntity(new ResponseMessage(true, "User with this Id is Updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteUser(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorCodes.USER_NOT_FOUND);
        }
        logger.info("UserServiceImpl || deleteUser || User has been Deleted Successfully");
        return new ResponseEntity<>(new ResponseMessage(true, "User" + id + " is Deleted"), HttpStatus.OK);
    }

    @Override
    public User getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.USER_NOT_FOUND));
        logger.info("UserServiceImpl || getUserById || Get user with ID");
        return user;
    }
}
