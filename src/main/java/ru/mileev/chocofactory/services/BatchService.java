package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.repositories.BatchRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository repository;

    public List<Batch> findAllByFormedNullOrFormedFalse() {
        return repository.findAllByFormedNullOrFormedFalse();
    }

    public Batch findById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Batch save(Batch batch) {
        return repository.save(batch);
    }

    public List<Batch> findAllByCreationDateBetween(LocalDate before, LocalDate after) {
        return repository.findAllByCreationDateBetween(before, after);
    }
}
