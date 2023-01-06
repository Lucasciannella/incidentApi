package com.diazero.incident.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class IncidentPutRequestBody {
    private Long idIncident;
    private String name;
    private String description;
}
