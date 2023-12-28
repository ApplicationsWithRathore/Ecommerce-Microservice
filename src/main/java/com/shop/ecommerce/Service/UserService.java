package com.shop.ecommerce.Service;

import com.shop.ecommerce.entity.User;
import com.shop.ecommerce.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto findUserById(Integer uId);

    List<UserDto> findAllUsers();

    void deleteUser();

    UserDto findByEmail();

}
