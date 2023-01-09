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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/incident-management/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping
    @Operation(summary = "Find all incidents", description = "it is recommended to use this method if you need to retrieve all records from the database")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    public ResponseEntity<List<Incident>> findAll() {
        return ResponseEntity.ok(incidentService.findAllNonPageable());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find incident by id", description = "This method will bring a specific record from the database if it exists")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<Incident> findById(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.findIncididentByIdOrThrowBadRequestException(id));
    }

    @GetMapping("/page-incidents")
    @Operation(summary = "List the last twenty incidents Paginated", description = "This method is pageable, by default it will bring the last 20 records from the table, however you can use url parameters to change the search and bring whatever is necessary for you")
    @ApiResponse(responseCode = "400", description = "When customer Does Not exist in database")
    public ResponseEntity<Page<Incident>> findPageableIncident(@ParameterObject @PageableDefault(page = 0, size = 20, sort = "idIncident", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(incidentService.findAllPageable(pageable));
    }

    @PostMapping
    @Operation(summary = "Create a new incident", description = "In this method you will add a new incident to your database")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<Incident> save( @Valid @RequestBody IncidentPostRequestBody incidentPostRequestBody) {
        return new ResponseEntity<>(incidentService.save(incidentPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update a incident", description = "This method is used to update the entire record of an incident")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public ResponseEntity<Incident> replace(@RequestBody @Valid IncidentPutRequestBody incidentPutRequestBody) {
        return ResponseEntity.ok(incidentService.replace(incidentPutRequestBody));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Close a incident", description = "This method is used to close a incident")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    public ResponseEntity<Void> close(@PathVariable Long id) {
         incidentService.close(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a incident",description = "This method excludes a unique incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When customer Does Not exist in database")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        incidentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}