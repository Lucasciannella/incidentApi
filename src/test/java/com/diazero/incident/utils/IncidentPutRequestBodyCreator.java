package com.diazero.incident.utils;

import com.diazero.incident.dto.IncidentPutRequestBody;

public class IncidentPutRequestBodyCreator {
    public static IncidentPutRequestBody createIncidentPutRequestBody() {
        return IncidentPutRequestBody.builder()
                .idIncident(IncidentCreator.createValidUpdatedIncident().getIdIncident())
                .name(IncidentCreator.createValidUpdatedIncident().getName())
                .description(IncidentCreator.createValidUpdatedIncident().getDescription())
                .status(IncidentCreator.createValidUpdatedIncident().getStatus())
                .build();
    }
}