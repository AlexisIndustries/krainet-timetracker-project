package com.alexisindustries.timetracker.models.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response Register User DTO model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @Schema(
            description = "Username of the user"
    )
    private String username;

    @Schema(
            description = "Password of the user"
    )
    private String password;

    @Schema(
            description = "Email of the user"
    )
    private String email;
}
