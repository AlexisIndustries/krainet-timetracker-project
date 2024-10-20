package com.alexisindustries.timetracker.controller;

import com.alexisindustries.timetracker.model.dto.project.ProjectDto;
import com.alexisindustries.timetracker.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Project Controller",
        description = "Project Controller exposes rest apis for project management"
)
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectController {
    private ProjectService projectService;

    @Operation(
            summary = "Creates new project",
            description = "Used to save new project to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Project created"
            )
    )
    @PostMapping("/save")
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto) {
        ProjectDto savedProject = projectService.saveProject(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Gets project by project's id",
            description = "Used to retrieve project from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Project found"
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findProjectById(@PathVariable Long id) {
        ProjectDto project = projectService.findProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @Operation(
            summary = "Gets all projects",
            description = "Used to retrieve all projects from database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Projects found"
            )
    )
    @GetMapping("/all")
    public ResponseEntity<List<ProjectDto>> findAllProjects() {
        List<ProjectDto> projects = projectService.findAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes project by project's id",
            description = "Used to delete project from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Project deleted"
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Updates project by project's id",
            description = "Used to update project from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Project found"
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto,
                                             @PathVariable Long id) {
        ProjectDto updatedProject = projectService.updateProject(projectDto, id);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
}
