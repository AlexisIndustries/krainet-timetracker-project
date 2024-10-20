package com.alexisindustries.timetracker.mapper;

import com.alexisindustries.timetracker.model.Project;
import com.alexisindustries.timetracker.model.dto.project.ProjectDto;
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
