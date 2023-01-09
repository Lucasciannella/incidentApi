package com.diazero.incident.service;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;
import com.diazero.incident.exceptions.BadRequestException;
import com.diazero.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    private final IncidentTransformer incidentTransformer;

    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    public Page<Incident> findPageableIncident(Pageable pageable) {
        return incidentRepository.findAll(pageable);
    }

    public Incident findIncididentByIdOrThrowBadRequestException(Long id) {
        return incidentRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Incident its not discovered or dos not exists in database"));
    }

    public Incident save(IncidentPostRequestBody incidentPostRequestBody) {
        return incidentRepository.save(incidentTransformer.transform(incidentPostRequestBody));
    }

    public Incident replace(IncidentPutRequestBody incidentPutRequestBody) {
        Incident incident = findIncididentByIdOrThrowBadRequestException(incidentPutRequestBody.getIdIncident());
        return incidentRepository.save(incidentTransformer.transform(incidentPutRequestBody, incident));
    }

    public Incident close(Long id){
        Incident incident = findIncididentByIdOrThrowBadRequestException(id);
        return incidentRepository.save(incidentTransformer.transform(incident));
    }

    public void delete(Long id) {
        incidentRepository.delete(findIncididentByIdOrThrowBadRequestException(id));
    }
}