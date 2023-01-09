package com.diazero.incident.dto;

import com.diazero.incident.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class IncidentPostRequestBody {

    @Schema(name = "incident name dto")
    @NotNull(message = "invalid username, please try again")
    private String name;

    @Schema(name = "incident description dto")
    @NotNull(message = "invalid description, please try again ")
    private String description;

    @Schema(name = "Incident status")
    private Status status;
}