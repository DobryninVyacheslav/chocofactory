package ru.mileev.chocofactory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mileev.chocofactory.domain.Delivery;
import ru.mileev.chocofactory.repositories.DeliveryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DeliveryServiceTest {

    @InjectMocks
    DeliveryService service;
    @Mock
    DeliveryRepository repository;

    @Test
    void whenSaveDeliveryThenReturnSavedDelivery() {
        // Arrange
        Delivery delivery = new Delivery(null, "Order #1", null, null);
        when(repository.save(delivery)).thenReturn(delivery);

        // Act
        Delivery result = service.save(delivery);

        // Assert
        assertEquals(delivery, result);
    }
}