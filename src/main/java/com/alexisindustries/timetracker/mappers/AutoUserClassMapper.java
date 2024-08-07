package com.alexisindustries.timetracker.mappers;

import com.alexisindustries.timetracker.models.User;
import com.alexisindustries.timetracker.models.dto.user.LoginUserDto;
import com.alexisindustries.timetracker.models.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.models.dto.user.UserDto;
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
