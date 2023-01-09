package com.diazero.incident.service;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;

public interface IncidentTransformer {
    Incident transform(IncidentPostRequestBody incidentPostRequestBody);

    Incident transform(IncidentPutRequestBody incidentPutRequestBody, Incident incident);

    Incident transform(Incident incident);
}