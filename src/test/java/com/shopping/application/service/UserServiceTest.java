package com.shopping.application.service;

import com.shopping.application.user.dto.UserDto;
import com.shopping.application.user.entity.User;
import com.shopping.application.user.repository.UserRepository;
import com.shopping.application.user.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {


    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    User user = new User();
     UserDto userDto = new UserDto();
    UserDto userDto2 = new UserDto();

    List<User> users = new ArrayList<User>();

    @BeforeEach
    void setUp(){
        user.setUserId(1L);
        user.setFirstName("Anand");
        user.setLastName("Babu");
        user.setAddress("Madurai");
        user.setDob("27th May 2001");
        user.setEmail("anand@gmail.com");
        user.setGender("Male");
        user.setPassword("Anand@1234");
        user.setPhoneNumber("1234567890");
        user.setAdmin(true);
        users.add(user);

        //Dto
        userDto.setUserId(1L);
        userDto.setFirstName("Anand");
        userDto.setLastName("Babu");
        userDto.setAddress("Madurai");
        userDto.setDob("27th May 2001");
        userDto.setEmail("anand@gmail.com");
        userDto.setGender("Male");
        userDto.setPassword("Anand@1234");
        userDto.setPhoneNumber("1234567890");
        userDto.setAdmin(true);
        //Dto2
        userDto2.setUserId(1L);
        userDto2.setFirstName("Anand");
        userDto2.setLastName("Babu");
        userDto2.setAddress("Madurai");
        userDto2.setDob("27th May 2001");
        userDto2.setEmail("anand@gmail.com");
        userDto2.setGender("Male");
        userDto2.setPassword("Anand@1234");
        userDto2.setPhoneNumber("1234567890");
        userDto2.setAdmin(true);
    }
    @Test
    void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(users);
        assertThat(userService.getAllUsers()).isEqualTo(users);
    }
    @Test
    void getUserById(){
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUserById(2L)).isEqualTo(user);
    }

    @Test
    void updateUser(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.updateUser(userDto).getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void updateUserFail(){
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userDto));
    }
    @Test
    void saveUser(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.saveUser(userDto).getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void saveUserFail(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userDto2));
    }
    @Test
    void deleteByUserId(){
        userService.deleteUser(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(any());
    }
   }
