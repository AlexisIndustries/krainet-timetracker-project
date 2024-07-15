package com.alexisindustries.timetracker.models.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response Project DTO model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    @Schema(
            description = "Identifier of the project"
    )
    private Long id;

    @Schema(
            description = "Name of the project"
    )
    private String name;

    @Schema(
            description = "Description of the project"
    )
    private String description;
}