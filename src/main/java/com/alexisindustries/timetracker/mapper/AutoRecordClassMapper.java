package com.alexisindustries.timetracker.mapper;

import com.alexisindustries.timetracker.model.Record;
import com.alexisindustries.timetracker.model.dto.record.RecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutoRecordClassMapper {
    AutoRecordClassMapper MAPPER = Mappers.getMapper(AutoRecordClassMapper.class);

    RecordDto mapToRecordDto(Record savedRecord);
    Record mapToRecord(RecordDto recordDto);
}
