package com.diazero.incident.service;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;
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
                .build();
    }
}