package com.alexisindustries.timetracker.model.dto.user;

import com.alexisindustries.timetracker.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Schema(
        description = "Response User DTO model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Schema(
            description = "Unique identifier of the user",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

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

    @Schema(
            description = "Roles assigned to the user"
    )
    private Set<Role> roles = new HashSet<>();
}
