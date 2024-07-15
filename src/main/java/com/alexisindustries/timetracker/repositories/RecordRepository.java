package com.alexisindustries.timetracker.repositories;

import com.alexisindustries.timetracker.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Optional<Record> findById(long id);
}
