package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Report;
import ru.mileev.chocofactory.repositories.ReportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository repository;

    public void create(Report report) {
        repository.save(report);
    }

    public List<Report> findAll() {
        return repository.findAll();
    }
}
