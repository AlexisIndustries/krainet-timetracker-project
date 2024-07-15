package com.alexisindustries.timetracker.models.dto;

import com.alexisindustries.timetracker.models.dto.project.ProjectDto;
import com.alexisindustries.timetracker.models.dto.record.RecordDto;
import com.alexisindustries.timetracker.models.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private RecordDto recordDto;
    private ProjectDto projectDto;
    private UserDto userDto;
}
