package com.alexisindustries.timetracker.mapper;

import com.alexisindustries.timetracker.model.User;
import com.alexisindustries.timetracker.model.dto.user.LoginUserDto;
import com.alexisindustries.timetracker.model.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.model.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutoUserClassMapper {
    AutoUserClassMapper MAPPER = Mappers.getMapper(AutoUserClassMapper.class);

    UserDto mapToUserDto(User user);
    UserDto mapToUserDto(RegisterUserDto registerUserDto);
    User mapToUser(LoginUserDto loginUserDto);
    User mapToUser(RegisterUserDto loginUserDto);
    User mapToUser(UserDto userDtoToSave);
}
