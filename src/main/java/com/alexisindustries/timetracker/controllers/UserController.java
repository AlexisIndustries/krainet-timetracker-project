package com.alexisindustries.timetracker.controllers;

import com.alexisindustries.timetracker.models.dto.user.UserDto;
import com.alexisindustries.timetracker.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "User Controller",
        description = "User Controller exposes rest apis for user management"
)
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Gets user by user's id",
            description = "Used to retrieve user from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "User found"
            )
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        UserDto user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Gets all users",
            description = "Used to retrieve all users from database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Users found"
            )
    )
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes user by user's id",
            description = "Used to delete user from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "204",
                    description = "User deleted"
            )
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Updates user by user's id",
            description = "Used to update user from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "User found"
            )
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                          @PathVariable Long id) {
        UserDto updatedUser = userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
