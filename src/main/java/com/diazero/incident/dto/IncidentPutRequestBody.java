package com.diazero.incident.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class IncidentPutRequestBody {

    @Schema(name = "Incident Id dto")
    @NotNull
    private Long idIncident;

    @Schema(name = "Incident name  dto")
    @NotNull
    private String name;

    @Schema(name = "Incident description")
    @NotNull
    private String description;
}