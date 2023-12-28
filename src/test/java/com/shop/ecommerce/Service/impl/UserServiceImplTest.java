package com.shop.ecommerce.Service.impl;

import com.shop.ecommerce.entity.User;
import com.shop.ecommerce.payloads.UserDto;
import com.shop.ecommerce.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Mock
    private UserRepo userRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;
    @BeforeEach
    void setUp() {
        userDto = UserDto.builder().userName("ja").email("").password("123").build();
        user = User.builder().userName("ja").email("").password("123").build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() {
        //Assert
        when(userService.modelToDto(user)).thenReturn(userDto);
        when(userRepo.save(user)).thenReturn(user);
        when(userService.dtoToModel(userDto)).thenReturn(user);

        //Act
       UserDto savedTest =  userService.createUser(userDto);

       //Assert
        verify(userRepo).save(user);
        assertThat(savedTest).isNotNull();
        assertThat(savedTest).isEqualTo(userDto);
    }

    @Test
    void findUserById() {


//when(userRepo.findAll()).thenReturn(userDtos);
    }

    @Test
    void findAllUsers() {
        List<UserDto> userDtos = List.of(userDto);
        List<User> users = List.of(user);

        when(userRepo.findAll()).thenReturn(users);
        when(userService.modelToDto(user)).thenReturn(userDto);

        List<UserDto > userDtosList  = userService.findAllUsers();
        logger.info("{}",userDtosList);
        assertNotNull(userDtosList);
        assertThat(userDtosList).isEqualTo(userDtos);
    }

    @Test
    void deleteUser() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void modelToDto() {
        when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);

        UserDto userDto1 = userService.modelToDto(user);

        assertThat(userDto1).isEqualTo(userDto);
    }

    @Test
    void dtoToModel() {
        when(modelMapper.map(userDto,User.class)).thenReturn(user);

        User user1 = userService.dtoToModel(userDto);

        assertNotNull(user1);
        assertThat(user1).isEqualTo(user);
    }
}