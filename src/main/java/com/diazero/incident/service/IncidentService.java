package com.diazero.incident.service;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;
import com.diazero.incident.exceptions.BadRequestException;
import com.diazero.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    public Page<Incident> findPageableIncident(Pageable pageable) {
        return incidentRepository.findAll(PageRequest.of(0, 20, Sort.by("createdAt").descending()));
    }

    public Incident findIncididentByIdOrThrowBadRequestException(Long id) {
        return incidentRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Incident its not discovered or dos not exists in database"));
    }

    public Incident save(IncidentPostRequestBody incidentPostRequestBody) {

        Incident incident = Incident.builder()
                .name(incidentPostRequestBody.getName())
                .description(incidentPostRequestBody.getDescription())
                .createdAt(LocalDate.now())
                .build();

        return incidentRepository.save(incident);
    }

    public Incident replace(IncidentPutRequestBody incidentPutRequestBody) {

        findIncididentByIdOrThrowBadRequestException(incidentPutRequestBody.getIdIncident());

        Incident incident = Incident.builder()
                .idIncident(incidentPutRequestBody.getIdIncident())
                .name(incidentPutRequestBody.getName())
                .description(incidentPutRequestBody.getDescription())
                .createdAt(findIncididentByIdOrThrowBadRequestException(incidentPutRequestBody.getIdIncident()).getCreatedAt())
                .updatedAt(LocalDate.now())
                .build();

        return incidentRepository.save(incident);
    }

    public void delete(Long id) {
        incidentRepository.delete(findIncididentByIdOrThrowBadRequestException(id));
    }
}