package com.alexisindustries.timetracker.service.impl;

import com.alexisindustries.timetracker.exception.ObjectNotFoundInDatabaseException;
import com.alexisindustries.timetracker.exception.RecordAlreadyExistsException;
import com.alexisindustries.timetracker.mapper.AutoRecordClassMapper;
import com.alexisindustries.timetracker.model.Record;
import com.alexisindustries.timetracker.model.dto.ResponseDto;
import com.alexisindustries.timetracker.model.dto.project.ProjectDto;
import com.alexisindustries.timetracker.model.dto.record.RecordDto;
import com.alexisindustries.timetracker.model.dto.user.UserDto;
import com.alexisindustries.timetracker.repository.RecordRepository;
import com.alexisindustries.timetracker.service.ProjectService;
import com.alexisindustries.timetracker.service.RecordService;
import com.alexisindustries.timetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private UserService userService;
    private ProjectService projectService;

    @Transactional
    @Override
    public RecordDto saveRecord(RecordDto recordDto) {

        Optional<Record> optionalRecord = recordRepository.findById(recordDto.getId());

        if (optionalRecord.isPresent()) {
            throw new RecordAlreadyExistsException("Record already exists");
        }

        Record record = AutoRecordClassMapper.MAPPER.mapToRecord(recordDto);
        Record savedRecord = recordRepository.save(record);
        return AutoRecordClassMapper.MAPPER.mapToRecordDto(savedRecord);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseDto findRecordById(Long id) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Record", "id", String.valueOf(id))
        );

        RecordDto recordDto = AutoRecordClassMapper.MAPPER.mapToRecordDto(record);
        UserDto userDto = userService.findUserById(record.getUserId());
        ProjectDto projectDto = projectService.findProjectById(record.getProjectId());

        return new ResponseDto(recordDto, projectDto, userDto);
    }

    @Transactional
    @Override
    public RecordDto updateRecord(Long id, RecordDto recordDto) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Record", "id", String.valueOf(id))
        );

        record.setStartTime(recordDto.getStartTime());
        record.setEndTime(recordDto.getEndTime());
        record.setUserId(recordDto.getUserId());
        record.setProjectId(recordDto.getProjectId());

        Record savedRecord = recordRepository.save(record);
        return AutoRecordClassMapper.MAPPER.mapToRecordDto(savedRecord);
    }

    @Transactional
    @Override
    public void deleteRecord(Long id) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Record", "id", String.valueOf(id))
        );

        recordRepository.delete(record);
    }
}
