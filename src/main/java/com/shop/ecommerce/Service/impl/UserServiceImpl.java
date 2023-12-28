package com.shop.ecommerce.Service.impl;

import com.shop.ecommerce.Service.UserService;
import com.shop.ecommerce.entity.User;
import com.shop.ecommerce.payloads.UserDto;
import com.shop.ecommerce.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToModel(userDto);
        User savedUser = this.userRepo.save(user);

        return this.modelToDto(savedUser);
    }

    @Override
    public UserDto findUserById(Integer uId) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtoList = users.stream().map(this::modelToDto).toList();
        return userDtoList;
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public UserDto findByEmail() {
        return null;
    }

    public UserDto modelToDto(User user){

    return this.modelMapper.map(user,UserDto.class);
    }
    public User dtoToModel(UserDto userDto){
        return this.modelMapper.map(userDto,User.class);
    }
}
