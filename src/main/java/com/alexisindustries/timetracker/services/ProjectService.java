package com.alexisindustries.timetracker.services;

import com.alexisindustries.timetracker.models.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto saveProject(ProjectDto projectDto);
    ProjectDto updateProject(ProjectDto projectDto, Long id);
    List<ProjectDto> findAllProjects();
    ProjectDto findProjectById(Long id);
    void deleteProjectById(Long id);
}
