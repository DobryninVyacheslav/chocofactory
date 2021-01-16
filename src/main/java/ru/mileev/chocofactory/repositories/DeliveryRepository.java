package ru.mileev.chocofactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mileev.chocofactory.domain.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
