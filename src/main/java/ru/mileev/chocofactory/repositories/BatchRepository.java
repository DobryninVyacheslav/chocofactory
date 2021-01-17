package ru.mileev.chocofactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mileev.chocofactory.domain.Batch;

import java.time.LocalDate;
import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    List<Batch> findAllByFormedNullOrFormedFalse();

    List<Batch> findAllByCreationDateBetween(LocalDate before, LocalDate after);
}
