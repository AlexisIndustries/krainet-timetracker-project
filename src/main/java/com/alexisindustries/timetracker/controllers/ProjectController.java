package com.alexisindustries.timetracker.controllers;

import com.alexisindustries.timetracker.models.dto.project.ProjectDto;
import com.alexisindustries.timetracker.services.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectController {

    private ProjectService projectService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectDto projectDto) {
        ProjectDto savedProject = projectService.saveProject(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        ProjectDto project = projectService.findProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projects = projectService.findAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto projectDto,
                                             @PathVariable Long id) {
        ProjectDto updatedProject = projectService.updateProject(projectDto, id);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
}
