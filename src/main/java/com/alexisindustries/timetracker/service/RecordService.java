package com.alexisindustries.timetracker.service;

import com.alexisindustries.timetracker.model.dto.ResponseDto;
import com.alexisindustries.timetracker.model.dto.record.RecordDto;

public interface RecordService {
    RecordDto saveRecord(RecordDto recordDto);
    ResponseDto findRecordById(Long id);
    RecordDto updateRecord(Long id, RecordDto recordDto);
    void deleteRecord(Long id);
}
