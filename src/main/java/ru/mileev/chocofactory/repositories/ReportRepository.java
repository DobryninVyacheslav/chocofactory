package ru.mileev.chocofactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mileev.chocofactory.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
