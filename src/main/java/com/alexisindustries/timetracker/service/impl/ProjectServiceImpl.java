package com.alexisindustries.timetracker.service.impl;

import com.alexisindustries.timetracker.exception.ObjectNotFoundInDatabaseException;
import com.alexisindustries.timetracker.exception.ProjectAlreadyExistsException;
import com.alexisindustries.timetracker.mapper.AutoProjectClassMapper;
import com.alexisindustries.timetracker.model.Project;
import com.alexisindustries.timetracker.model.dto.project.ProjectDto;
import com.alexisindustries.timetracker.repository.ProjectRepository;
import com.alexisindustries.timetracker.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Transactional
    @Override
    public ProjectDto saveProject(ProjectDto projectDto) {
        Optional<Project> optionalProject = projectRepository.findByName(projectDto.getName());

        if (optionalProject.isPresent()) {
            throw new ProjectAlreadyExistsException("Project already exists");
        }

        Project project = AutoProjectClassMapper.MAPPER.mapToProject(projectDto);
        Project savedProject = projectRepository.save(project);
        return AutoProjectClassMapper.MAPPER.mapToProjectDto(savedProject);
    }

    @Transactional
    @Override
    public ProjectDto updateProject(ProjectDto projectDto, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Project", "id", String.valueOf(id))
        );

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());

        Project updatedProject = projectRepository.save(project);
        return AutoProjectClassMapper.MAPPER.mapToProjectDto(updatedProject);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(AutoProjectClassMapper.MAPPER::mapToProjectDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProjectDto findProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Project", "id", String.valueOf(id))
        );

        return AutoProjectClassMapper.MAPPER.mapToProjectDto(project);
    }

    @Transactional
    @Override
    public void deleteProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Project", "id", String.valueOf(id))
        );

        projectRepository.delete(project);
    }
}
