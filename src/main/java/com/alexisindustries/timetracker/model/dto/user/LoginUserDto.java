package com.alexisindustries.timetracker.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response Login User DTO model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    @Schema(
            description = "Username of the user"
    )
    private String username;

    @Schema(
            description = "Password of the user"
    )
    private String password;
}
