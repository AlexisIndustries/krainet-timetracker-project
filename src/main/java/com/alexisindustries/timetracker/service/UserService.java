package com.alexisindustries.timetracker.service;

import com.alexisindustries.timetracker.model.dto.user.LoginUserDto;
import com.alexisindustries.timetracker.model.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.model.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto saveUser(RegisterUserDto registerUserDto);
    void deleteUserById(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
    String loginUser(LoginUserDto loginUserDto);
}
