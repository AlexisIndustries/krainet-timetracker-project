package com.alexisindustries.timetracker.service;

import com.alexisindustries.timetracker.model.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto saveProject(ProjectDto projectDto);
    ProjectDto updateProject(ProjectDto projectDto, Long id);
    List<ProjectDto> findAllProjects();
    ProjectDto findProjectById(Long id);
    void deleteProjectById(Long id);
}
