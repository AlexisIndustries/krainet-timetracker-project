package com.alexisindustries.timetracker.services.impls;

import com.alexisindustries.timetracker.exceptions.ObjectNotFoundInDatabaseException;
import com.alexisindustries.timetracker.exceptions.RecordAlreadyExistsException;
import com.alexisindustries.timetracker.mappers.AutoRecordClassMapper;
import com.alexisindustries.timetracker.models.Record;
import com.alexisindustries.timetracker.models.dto.ResponseDto;
import com.alexisindustries.timetracker.models.dto.project.ProjectDto;
import com.alexisindustries.timetracker.models.dto.record.RecordDto;
import com.alexisindustries.timetracker.models.dto.user.UserDto;
import com.alexisindustries.timetracker.repositories.RecordRepository;
import com.alexisindustries.timetracker.services.ProjectService;
import com.alexisindustries.timetracker.services.RecordService;
import com.alexisindustries.timetracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private UserService userService;
    private ProjectService projectService;

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

    @Override
    public void deleteRecord(Long id) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundInDatabaseException("Record", "id", String.valueOf(id))
        );

        recordRepository.delete(record);
    }
}
