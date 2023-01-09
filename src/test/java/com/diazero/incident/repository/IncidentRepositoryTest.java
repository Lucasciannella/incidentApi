package com.diazero.incident.repository;

import com.diazero.incident.entity.Incident;
import com.diazero.incident.utils.IncidentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class IncidentRepositoryTest {

    @Autowired
    IncidentRepository incidentRepository;

    @Test
    @DisplayName("Save creates incident when successful")
    void save_PersitsIncident_WhenSuccessful() {
        var incidentToBeSaved = IncidentCreator.createIncidentToBeSaved();

        var incidentSaved = incidentRepository.save(incidentToBeSaved);

        Assertions.assertThat(incidentSaved).isNotNull();

        Assertions.assertThat(incidentSaved.getName()).isEqualTo(incidentToBeSaved.getName());

        Assertions.assertThat(incidentSaved.getDescription()).isEqualTo(incidentToBeSaved.getDescription());

        Assertions.assertThat(incidentSaved.getStatus()).isEqualTo(incidentToBeSaved.getStatus());

        Assertions.assertThat(incidentSaved.getCreatedAt()).isEqualTo(incidentToBeSaved.getCreatedAt());
    }

    @Test
    @DisplayName("Save update incident when successful")
    void update_PersitsIncident_WhenSuccessful() {
        var incidentToBeSaved = IncidentCreator.createValidUpdatedIncident();

        var incidentSaved = incidentRepository.save(incidentToBeSaved);

        Assertions.assertThat(incidentSaved).isNotNull();

        Assertions.assertThat(incidentSaved.getIdIncident()).isNotNull();

        Assertions.assertThat(incidentSaved.getName()).isEqualTo(incidentToBeSaved.getName());

        Assertions.assertThat(incidentSaved.getDescription()).isEqualTo(incidentToBeSaved.getDescription());

        Assertions.assertThat(incidentSaved.getStatus()).isEqualTo(incidentToBeSaved.getStatus());

        Assertions.assertThat(incidentSaved.getCreatedAt()).isEqualTo(incidentToBeSaved.getCreatedAt());
    }

    @Test
    @DisplayName("removes  incident when successful")
    void remove_PersitsIncident_WhenSuccessful() {
        var incidentToBeSaved = IncidentCreator.createValidUpdatedIncident();

        var incidentSaved = incidentRepository.save(incidentToBeSaved);

        this.incidentRepository.delete(incidentSaved);

        Optional<Incident> incidentOptional = this.incidentRepository.findById(incidentSaved.getIdIncident());

        Assertions.assertThat(incidentOptional.isEmpty()).isTrue();
    }
}