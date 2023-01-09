package com.diazero.incident.controller;

import com.diazero.incident.dto.IncidentPostRequestBody;
import com.diazero.incident.dto.IncidentPutRequestBody;
import com.diazero.incident.entity.Incident;
import com.diazero.incident.service.IncidentService;
import com.diazero.incident.utils.IncidentCreator;
import com.diazero.incident.utils.IncidentPostRequestBodyCreator;
import com.diazero.incident.utils.IncidentPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class IncidentControllerTest {

    @InjectMocks
    private IncidentController incidentController;

    @Mock
    private IncidentService incidentService;

    @BeforeEach
    void setUp() {
        PageImpl<Incident> page = new PageImpl<>(List.of(IncidentCreator.createValidIncident()));
        BDDMockito.when(incidentService.findAllPageable(ArgumentMatchers.any())).thenReturn(page);

        BDDMockito.when(incidentService.findAllNonPageable()).thenReturn(List.of(IncidentCreator.createValidIncident()));

        BDDMockito.when(incidentService.findIncididentByIdOrThrowBadRequestException(ArgumentMatchers.anyLong())).thenReturn(IncidentCreator.createValidIncident());

        BDDMockito.when(incidentService.save(ArgumentMatchers.any(IncidentPostRequestBody.class))).thenReturn(IncidentCreator.createValidIncident());

        BDDMockito.when(incidentService.replace(ArgumentMatchers.any(IncidentPutRequestBody.class))).thenReturn(IncidentCreator.createValidUpdatedIncident());

        BDDMockito.doNothing().when(incidentService).delete(ArgumentMatchers.anyLong());

        BDDMockito.doNothing().when(incidentService).close(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Retun list of incidents inside page objects when successful")
    void list_ReturnListOfIncidentsInsidePageObjects_WhenSuccessful() {
        var expectedIncident = IncidentCreator.createValidIncident();
        var incidentPage = incidentController.findPageableIncident(null).getBody();

        Assertions.assertThat(incidentPage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(incidentPage.toList().get(0).getName()).isEqualTo(expectedIncident.getName());
    }

    @Test
    @DisplayName("Return List of incidents when successful")
    void list_ReturnListOfIncidents_WhenSuccessful() {
        var expectedIncident = IncidentCreator.createValidIncident();
        var incidentList = incidentController.findAll().getBody();

        Assertions.assertThat(incidentList).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(incidentList.get(0).getName()).isEqualTo(expectedIncident.getName());
    }

    @Test
    @DisplayName("Return incident when succesfull")
    void findByid_ReturnsIncident_WhenSuccessful() {
        var expectedIncident = IncidentCreator.createValidIncident();
        var incident = incidentController.findById(1L).getBody();

        Assertions.assertThat(incident).isNotNull();
        Assertions.assertThat(incident.getIdIncident()).isNotNull().isEqualTo(expectedIncident.getIdIncident());
    }

    @Test
    @DisplayName("Save return incident when succesfull")
    void save_WhenSuccessful() {

        var incident = incidentController.save(IncidentPostRequestBodyCreator.createIncidentPostRequestBody()).getBody();

        Assertions.assertThat(incident).isNotNull();
        Assertions.assertThat(incident.getName()).isEqualTo(IncidentCreator.createValidIncident().getName());
        Assertions.assertThat(incident.getStatus()).isEqualTo(IncidentCreator.createValidIncident().getStatus());
        Assertions.assertThat(incident.getDescription()).isEqualTo(IncidentCreator.createValidIncident().getDescription());
    }

    @Test
    @DisplayName("update return incident when succesfull")
    void updated_WhenSuccessful() {

        var incident = incidentController.replace(IncidentPutRequestBodyCreator.createIncidentPutRequestBody()).getBody();

        Assertions.assertThat(incident).isNotNull();
        Assertions.assertThat(incident.getName()).isEqualTo(IncidentCreator.createValidUpdatedIncident().getName());
        Assertions.assertThat(incident.getStatus()).isEqualTo(IncidentCreator.createValidUpdatedIncident().getStatus());
        Assertions.assertThat(incident.getDescription()).isEqualTo(IncidentCreator.createValidUpdatedIncident().getDescription());
    }

    @Test
    @DisplayName("delete removes incident when succesful")
    void delete_removesIncident_WhenSuccesful() {

        Assertions.assertThatCode(() -> incidentController.close(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = incidentController.close(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes incident when succesful")
    void close_ClosedIncident_WhenSuccesful() {

        Assertions.assertThatCode(() -> incidentController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = incidentController.delete(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}