package com.diazero.incident.utils;

import com.diazero.incident.entity.Incident;
import com.diazero.incident.entity.Status;

import java.time.LocalDate;

public class IncidentCreator {

    public static Incident createValidIncident() {
        return Incident.builder()
                .idIncident(1L)
                .name("Testing incident")
                .description("Testing description")
                .createdAt(LocalDate.now())
                .status(Status.NEW)
                .build();
    }

    public static Incident createValidUpdatedIncident() {
        return Incident.builder()
                .idIncident(1L)
                .name("Testing incident")
                .description("Testing description")
                .updatedAt(LocalDate.now())
                .status(Status.UPDATED)
                .build();
    }

    public static Incident createIncidentToBeSaved() {

        return Incident.builder()
                .name("Testing incident")
                .description("Testing description")
                .createdAt(LocalDate.now())
                .status(Status.NEW)
                .build();
    }
}