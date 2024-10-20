package com.alexisindustries.timetracker.service.impl;

import com.alexisindustries.timetracker.exception.BadCredentialsException;
import com.alexisindustries.timetracker.exception.UserAlreadyExistsException;
import com.alexisindustries.timetracker.exception.ObjectNotFoundInDatabaseException;
import com.alexisindustries.timetracker.mapper.AutoUserClassMapper;
import com.alexisindustries.timetracker.model.Role;
import com.alexisindustries.timetracker.model.User;
import com.alexisindustries.timetracker.model.dto.user.LoginUserDto;
import com.alexisindustries.timetracker.model.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.model.dto.user.UserDto;
import com.alexisindustries.timetracker.repository.UserRepository;
import com.alexisindustries.timetracker.security.jwt.JwtTokenManager;
import com.alexisindustries.timetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JwtTokenManager jwtTokenManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
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

        UserDto userDto1 = AutoUserClassMapper.MAPPER.mapToUserDto(savedUser);
        userDto1.setPassword("[protected]");

        return userDto1;
    }

    @Transactional
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

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(AutoUserClassMapper.MAPPER::mapToUserDto).peek((u) -> u.setPassword("[protected]")).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("User", "id", String.valueOf(id))
        );
        return AutoUserClassMapper.MAPPER.mapToUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("User", "id", String.valueOf(id))
        );

        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public String loginUser(LoginUserDto dto) {
        UserDetails user = loadUserByUsername(dto.getUsername());

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            Set<Role> authorities = (Set<Role>) user.getAuthorities();
            return jwtTokenManager.createToken(user.getUsername(), user.getPassword(), authorities);
        }

        throw new BadCredentialsException();
    }

    @Transactional(readOnly = true)
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
