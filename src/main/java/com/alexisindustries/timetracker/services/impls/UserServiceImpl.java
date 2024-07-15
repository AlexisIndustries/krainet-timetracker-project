package com.alexisindustries.timetracker.services.impls;

import com.alexisindustries.timetracker.exceptions.UserAlreadyExistsException;
import com.alexisindustries.timetracker.exceptions.ObjectNotFoundInDatabaseException;
import com.alexisindustries.timetracker.mappers.AutoUserClassMapper;
import com.alexisindustries.timetracker.models.Role;
import com.alexisindustries.timetracker.models.User;
import com.alexisindustries.timetracker.models.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.models.dto.user.UserDto;
import com.alexisindustries.timetracker.repositories.UserRepository;
import com.alexisindustries.timetracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(RegisterUserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User " + userDto.getUsername() + " already exists");
        }

        UserDto userDtoToSave = AutoUserClassMapper.MAPPER.mapToUserDto(userDto);

        userDtoToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDtoToSave.getRoles().add(Role.USER);

        User user = AutoUserClassMapper.MAPPER.mapToUser(userDtoToSave);
        User savedUser = userRepository.save(user);

        return AutoUserClassMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("User", "id", String.valueOf(id))
        );

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return AutoUserClassMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(AutoUserClassMapper.MAPPER::mapToUserDto).toList();
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("User", "id", String.valueOf(id))
        );
        return AutoUserClassMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("User", "id", String.valueOf(id))
        );

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .email(user.getEmail())
                .build();
    }
}
