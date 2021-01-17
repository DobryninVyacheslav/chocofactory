package ru.mileev.chocofactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mileev.chocofactory.domain.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByBatchFormedNullOrBatchFormedFalse();

    List<Notification> findAllByBatchIdAndBatchFormedNullOrBatchFormedFalse(Long batchId);

}
