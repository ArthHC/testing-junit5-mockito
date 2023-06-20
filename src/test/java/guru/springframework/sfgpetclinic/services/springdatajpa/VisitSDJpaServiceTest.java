package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit visit = new Visit();

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();

        assertThat(foundVisits).hasSize(1);
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1l);

        assertThat(foundVisit).isNotNull();
        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());

        verify(visitRepository).save(any(Visit.class));

        assertThat(savedVisit).isNotNull();


    }

    @Test
    void delete() {
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
        verify(visitRepository).deleteById(anyLong());
    }
}