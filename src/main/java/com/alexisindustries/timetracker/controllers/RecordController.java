package com.alexisindustries.timetracker.controllers;

import com.alexisindustries.timetracker.models.dto.ResponseDto;
import com.alexisindustries.timetracker.models.dto.record.RecordDto;
import com.alexisindustries.timetracker.services.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Record Controller",
        description = "Record Controller exposes rest apis for record management"
)
@RestController
@RequestMapping("/api/v1/records")
@AllArgsConstructor
@Slf4j
public class RecordController {
    private RecordService recordService;

    @Operation(
            summary = "Creates new record",
            description = "Used to save new record to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Record created"
            )
    )
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RecordDto> saveRecord(@RequestBody RecordDto recordDto) {
        RecordDto savedRecord = recordService.saveRecord(recordDto);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Gets record by its id",
            description = "Used to retrieve record from database by record's id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Record found"
            )
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ResponseDto> findRecordById(@PathVariable Long id) {
        ResponseDto apiResponseDto = recordService.findRecordById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update record by record's id",
            description = "Used to update record from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Record found"
            )
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<RecordDto> updateRecord(@PathVariable Long id,
                                            @RequestBody RecordDto recordDto) {
        RecordDto updatedRecord = recordService.updateRecord(id, recordDto);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes record by record's id",
            description = "Used to delete record from database by its id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Record deleted"
            )
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRecordById(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
