package com.alexisindustries.timetracker.repositories;

import com.alexisindustries.timetracker.models.Project;
import com.alexisindustries.timetracker.models.dto.project.ProjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByName(String name);
    Optional<Project> findById(Long id);
}
