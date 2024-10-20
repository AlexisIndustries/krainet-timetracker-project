package com.alexisindustries.timetracker.model.dto;

import com.alexisindustries.timetracker.model.dto.project.ProjectDto;
import com.alexisindustries.timetracker.model.dto.record.RecordDto;
import com.alexisindustries.timetracker.model.dto.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response API DTO model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Record response data"
    )
    private RecordDto recordDto;
    @Schema(
            description = "Project response data"
    )
    private ProjectDto projectDto;
    @Schema(
            description = "User response data"
    )
    private UserDto userDto;
}
