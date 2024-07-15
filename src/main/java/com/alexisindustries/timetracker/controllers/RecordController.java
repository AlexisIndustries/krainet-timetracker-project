package com.alexisindustries.timetracker.controllers;

import com.alexisindustries.timetracker.models.dto.ResponseDto;
import com.alexisindustries.timetracker.models.dto.record.RecordDto;
import com.alexisindustries.timetracker.services.RecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/records")
@AllArgsConstructor
@Slf4j
public class RecordController {
    private RecordService recordService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RecordDto> save(@RequestBody RecordDto recordDto) {
        RecordDto savedRecord = recordService.saveRecord(recordDto);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ResponseDto> findById(@PathVariable Long id) {
        ResponseDto apiResponseDto = recordService.findRecordById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RecordDto> update(@PathVariable Long id,
                                            @RequestBody RecordDto recordDto) {
        RecordDto updatedRecord = recordService.updateRecord(id, recordDto);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
