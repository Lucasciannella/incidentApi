package com.diazero.incident.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {

    protected String title;

    protected int status;

    protected String developerMessage;

    protected LocalDateTime timestamp;

    protected String details;
}