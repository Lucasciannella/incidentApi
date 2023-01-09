package com.diazero.incident.service;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;
import com.diazero.incident.entity.Status;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class IncidentTransformerImpl implements IncidentTransformer {
    @Override
    public Incident transform(IncidentPostRequestBody incidentPostRequestBody) {

        return Incident.builder()
                .name(incidentPostRequestBody.getName())
                .description(incidentPostRequestBody.getDescription())
                .createdAt(LocalDate.now())
                .status(Status.NEW)
                .build();
    }

    @Override
    public Incident transform(IncidentPutRequestBody incidentPutRequestBody, Incident incident) {

        return Incident.builder()
                .idIncident(incidentPutRequestBody.getIdIncident())
                .name(incidentPutRequestBody.getName())
                .description(incidentPutRequestBody.getDescription())
                .createdAt(incident.getCreatedAt())
                .updatedAt(LocalDate.now())
                .status(Status.UPDATED)
                .build();
    }

    @Override
    public Incident transform(Incident incident) {
        return Incident.builder()
                .idIncident(incident.getIdIncident())
                .name(incident.getName())
                .description(incident.getDescription())
                .createdAt(incident.getCreatedAt())
                .updatedAt(incident.getUpdatedAt())
                .closedAt(LocalDate.now())
                .status(Status.CLOSED)
                .build();
    }


}