package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Notification;
import ru.mileev.chocofactory.repositories.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public List<Notification> findAllByBatchFormedNullOrBatchFormedFalse() {
        return formatDateTime(repository.findAllByBatchFormedNullOrBatchFormedFalse());
    }

    public void updateAllByBatchId(Long batchId) {
        formatDateTime(repository.findAllByBatchIdAndBatchFormedNullOrBatchFormedFalse(batchId))
                .forEach(n -> {
                    n.setBatchFormed(true);
                    repository.save(n);
                });
    }

    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    private List<Notification> formatDateTime(List<Notification> notifications) {
        return notifications.stream()
                .peek(n -> n.setCreationTime(LocalDateTime.parse(n.getCreationTime().format(formatter), formatter)))
                .collect(Collectors.toList());
    }
}
