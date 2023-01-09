package com.diazero.incident.utils;

import com.diazero.incident.dto.IncidentPostRequestBody;

public class IncidentPostRequestBodyCreator {
    public static IncidentPostRequestBody createIncidentPostRequestBody() {
        return IncidentPostRequestBody.builder()
                .name(IncidentCreator.createValidIncident().getName())
                .description(IncidentCreator.createValidIncident().getDescription())
                .status(IncidentCreator.createValidUpdatedIncident().getStatus())
                .build();
    }
}