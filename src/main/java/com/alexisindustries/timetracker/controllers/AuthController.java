package com.alexisindustries.timetracker.controllers;

import com.alexisindustries.timetracker.models.Role;
import com.alexisindustries.timetracker.models.dto.user.LoginUserDto;
import com.alexisindustries.timetracker.models.dto.user.RegisterUserDto;
import com.alexisindustries.timetracker.models.dto.user.UserDto;
import com.alexisindustries.timetracker.security.jwt.JwtTokenManager;
import com.alexisindustries.timetracker.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Tag(
        name = "Auth Controller",
        description = "Auth Controller exposes rest apis for authorization and authentication"
)
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager jwtTokenManager;

    @Operation(
            summary = "Creates new user",
            description = "Used to save new user to a database. Creates user with role 'USER'"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "User created"
            )
    )
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        UserDto createdUser = userService.saveUser(registerUserDto);
        createdUser.setPassword("[protected]");
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Logins new user and creates JWT Token",
            description = "Used to login new user and return JWT Token"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "User logged in successfully and token created"
            )
    )
    @PostMapping("/login")
    @SuppressWarnings("unchecked")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto dto) {
        UserDetails user = userService.loadUserByUsername(dto.getUsername());

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            Set<Role> authorities = (Set<Role>) user.getAuthorities();
            String token = jwtTokenManager.createToken(user.getUsername(), user.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}
