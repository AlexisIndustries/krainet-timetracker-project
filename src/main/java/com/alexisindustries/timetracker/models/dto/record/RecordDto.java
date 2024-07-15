package com.alexisindustries.timetracker.models.dto.record;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(
        description = "Response Record DTO model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {

    @Schema(
            description = "Identifier of the record"
    )
    private Long id;

    @Schema(
            description = "Start time of the record"
    )
    private LocalDateTime startTime;

    @Schema(
            description = "End time of the record"
    )
    private LocalDateTime endTime;

    @Schema(
            description = "Unique identifier of the user"
    )
    private Long userId;

    @Schema(
            description = "Unique identifier of the project"
    )
    private Long projectId;
}
