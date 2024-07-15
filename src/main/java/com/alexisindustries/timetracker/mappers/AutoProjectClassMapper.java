package com.alexisindustries.timetracker.mappers;

import com.alexisindustries.timetracker.models.Project;
import com.alexisindustries.timetracker.models.dto.project.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutoProjectClassMapper {
    AutoProjectClassMapper MAPPER = Mappers.getMapper(AutoProjectClassMapper.class);

    ProjectDto mapToProject(Project projectDto);
    Project mapToProjectDto(ProjectDto savedProjectDto);
    Project mapToProject(ProjectDto projectDto);
    ProjectDto mapToProjectDto(Project updatedProject);
}
