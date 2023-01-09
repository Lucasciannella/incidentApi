package com.diazero.incident.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class IncidentPutRequestBody {

    @Schema(name = "Incident Id dto")
    @NotNull(message = "Invalid id, please try again")
    private Long idIncident;

    @Schema(name = "Incident name  dto")
    @NotNull(message = "invalid name, please try again")
    private String name;

    @Schema(name = "Incident description")
    @NotNull(message = "invalid description, please try again")
    private String description;
}