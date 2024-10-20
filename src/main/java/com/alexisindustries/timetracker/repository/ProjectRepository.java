package com.alexisindustries.timetracker.repository;

import com.alexisindustries.timetracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByName(String name);
    Optional<Project> findById(Long id);
}
