package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Delivery;
import ru.mileev.chocofactory.repositories.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository repository;

    public Delivery save(Delivery delivery) {
        return repository.save(delivery);
    }
}
