package com.alexisindustries.timetracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDetailResponse {
    private LocalDateTime errorTime;
    private String message;
    private String path;
    private ErrorCode errorCode;
}
