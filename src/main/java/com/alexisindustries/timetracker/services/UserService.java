package com.alexisindustries.timetracker.services;

import com.alexisindustries.timetracker.models.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.models.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto saveUser(RegisterUserDto registerUserDto);
    void deleteUserById(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
}
