package com.shop.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.ecommerce.Service.impl.UserServiceImpl;
import com.shop.ecommerce.payloads.UserDto;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
@MockBean
    private UserServiceImpl userService;
@Autowired
private MockMvc mockMvc;
@Autowired
private ObjectMapper objectMapper;

private UserDto userDto;
    @BeforeEach
    void setUp() {
        userDto = UserDto.builder().userName("Ja").build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveUser() throws Exception {
    when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

    mockMvc.perform(post("/api/v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto))
            ).andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userName",CoreMatchers.is("Ja")));
    }

    @Test
    void getAll() throws Exception {
        List<UserDto> userDtoList = List.of(userDto);

        when(userService.findAllUsers()).thenReturn(userDtoList);

        mockMvc.perform(get("/api/v1")).andExpect(status().isOk()).andDo(print());
    }
}