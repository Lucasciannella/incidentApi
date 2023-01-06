package com.diazero.incident.controller;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;
import com.diazero.incident.service.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/incident-management")
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping("/incidents")
    public ResponseEntity<List<Incident>> findAll() {
        return ResponseEntity.ok(incidentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> findById(@PathVariable Long id){
        return ResponseEntity.ok(incidentService.findIncididentByIdOrThrowBadRequestException(id));
    }

    @GetMapping("/last-twenty/incidents")
    @Operation(summary = "List the last twenty incidents")
    public ResponseEntity<Page<Incident>> findPageableIncident(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(incidentService.findPageableIncident(pageable));
    }

    @PostMapping
    public ResponseEntity<Incident> save(@RequestBody IncidentPostRequestBody incidentPostRequestBody) {
        return new ResponseEntity<>(incidentService.save(incidentPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Incident> replace(@RequestBody IncidentPutRequestBody incidentPutRequestBody) {
        return ResponseEntity.ok(incidentService.replace(incidentPutRequestBody));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When incident does not exist in database")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        incidentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}