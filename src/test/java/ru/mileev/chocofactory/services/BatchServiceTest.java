package ru.mileev.chocofactory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.repositories.BatchRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BatchServiceTest {

    @InjectMocks
    BatchService service;
    @Mock
    BatchRepository repository;

    @Test
    void whenFoundAllBatchesByFormedNullOrFormedFalseThenReturnBatchList() {
        // Arrange
        when(repository.findAllByFormedNullOrFormedFalse())
                .thenReturn(List.of(Batch.builder().id(1L).build(),
                        Batch.builder().id(2L).build()));

        // Act
        List<Batch> result = service.findAllByFormedNullOrFormedFalse();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void whenFoundBatchByIdThenReturnBatch() {
        // Arrange
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(Batch.builder()
                .id(id)
                .build()));

        // Act
        Batch result = service.findById(id);

        // Assert
        assertEquals(id, result.getId());
    }

    @Test
    void whenBatchNotFoundByIdThenThrowException() {
        // Arrange
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act And Assert
        assertThrows(IllegalArgumentException.class, () -> service.findById(id));
    }

    @Test
    void whenSaveBatchThenReturnSavedBatch() {
        // Arrange
        Batch batch = Batch.builder()
                .formed(true)
                .build();
        when(repository.save(batch)).thenReturn(batch);

        // Act
        Batch result = service.save(batch);

        // Assert
        assertEquals(batch, result);
    }

    @Test
    void whenFoundAllBatchesByCreationDateBetweenThenReturnBatchList() {
        // Arrange
        LocalDate before = LocalDate.parse("2020-01-15");
        LocalDate after = LocalDate.parse("2020-01-20");
        when(repository.findAllByCreationDateBetween(before, after))
                .thenReturn(List.of(Batch.builder().id(1L).build(),
                        Batch.builder().id(2L).build()));

        // Act
        List<Batch> result = service.findAllByCreationDateBetween(before, after);

        // Assert
        assertEquals(2, result.size());
    }

}