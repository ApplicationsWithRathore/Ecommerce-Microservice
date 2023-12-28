package com.shop.ecommerce.controller;

import com.shop.ecommerce.Service.impl.UserServiceImpl;
import com.shop.ecommerce.entity.User;
import com.shop.ecommerce.payloads.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){
        UserDto user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> users = userService.findAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
