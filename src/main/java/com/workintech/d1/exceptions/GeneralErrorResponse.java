package com.workintech.d1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GeneralErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
}
