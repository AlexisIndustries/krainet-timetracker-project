package com.alexisindustries.timetracker.services.impls;

import com.alexisindustries.timetracker.exceptions.ObjectNotFoundInDatabaseException;
import com.alexisindustries.timetracker.exceptions.ProjectAlreadyExistsException;
import com.alexisindustries.timetracker.mappers.AutoProjectClassMapper;
import com.alexisindustries.timetracker.models.Project;
import com.alexisindustries.timetracker.models.dto.project.ProjectDto;
import com.alexisindustries.timetracker.repositories.ProjectRepository;
import com.alexisindustries.timetracker.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

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

    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(AutoProjectClassMapper.MAPPER::mapToProjectDto).toList();
    }

    @Override
    public ProjectDto findProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Project", "id", String.valueOf(id))
        );

        return AutoProjectClassMapper.MAPPER.mapToProjectDto(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Project", "id", String.valueOf(id))
        );

        projectRepository.delete(project);
    }
}
