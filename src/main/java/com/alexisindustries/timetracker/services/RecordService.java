package com.alexisindustries.timetracker.services;

import com.alexisindustries.timetracker.models.dto.ResponseDto;
import com.alexisindustries.timetracker.models.dto.record.RecordDto;

public interface RecordService {
    RecordDto saveRecord(RecordDto recordDto);
    ResponseDto findRecordById(Long id);
    RecordDto updateRecord(Long id, RecordDto recordDto);
    void deleteRecord(Long id);
}
