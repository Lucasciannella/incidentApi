package com.diazero.incident.dto;

import com.diazero.incident.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
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

    @Schema(name = "Incident status")
    private Status status;
}