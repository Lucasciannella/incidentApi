package com.diazero.incident.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncident;

    @Schema(name = "Incident name",example = "SQL INJECTION",required= true)
    private String name;

    @Schema(name = "Incident description",example = "SQL INJECTION are cybe security incident",required= true)
    private String description;

    @Schema(name = "Incident created date", example = "2023/01/07")
    private LocalDate createdAt;

    @Schema(name = "Incident updated date", example = "2023/01/07")
    private LocalDate updatedAt;

    @Schema(name = "Incident closed date", example = "2023/01/07")
    private LocalDate closedAt;

    @Schema(name= "Incident Status", example = "NEW")
    @Enumerated(EnumType.STRING)
    private Status status;
}