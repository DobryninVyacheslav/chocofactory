package ru.mileev.chocofactory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mileev.chocofactory.domain.Request;
import ru.mileev.chocofactory.repositories.RequestRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RequestServiceTest {
    @InjectMocks
    RequestService service;
    @Mock
    RequestRepository repository;

    @Test
    void whenSaveBatchThenReturnSavedBatch() {
        // Arrange
        Request request = new Request();
        when(repository.save(request)).thenReturn(request);

        // Act
        Request result = service.create(request);

        // Assert
        assertEquals(request, result);
    }

    @Test
    void whenIngredientsExistsThenReturnRequestsByIngredients() {
        // Arrange
        String ingredients = "Орехи";
        List<Request> requests = Collections.singletonList(new Request());
        when(repository.findAllByIngredients(ingredients))
                .thenReturn(requests);

        // Act
        List<Request> result = service.findByIngredients(ingredients);

        // Assert
        assertEquals(requests, result);
    }
    @Test
    void whenIngredientsNotExistsThenReturnAllRequests() {
        // Arrange
        String ingredients = null;
        List<Request> requests = Collections.singletonList(new Request());
        when(repository.findAll()).thenReturn(requests);

        // Act
        List<Request> result = service.findByIngredients(ingredients);

        // Assert
        assertEquals(requests, result);
    }

    @Test
    void whenFindAllThenReturnRequestList() {
        // Arrange
        List<Request> requests = Collections.singletonList(new Request());
        when(repository.findAll()).thenReturn(requests);

        // Act
        List<Request> result = service.readAll();

        // Assert
        assertEquals(requests, result);
    }
}