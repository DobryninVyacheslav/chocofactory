package ru.mileev.chocofactory.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.domain.Report;
import ru.mileev.chocofactory.repositories.ReportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository repository;
    private final BatchService batchService;

    public void create(Report report) {
        repository.save(report);
    }

    public List<Report> findAll() {
        return repository.findAll();
    }

    @SneakyThrows
    public String prepareReportData(LocalDate before, LocalDate after) {
        List<Batch> batches = batchService.findAllByCreationDateBetween(before, after);

        JsonArray jsonDays = new JsonArray();
        JsonArray jsonBatches = new JsonArray();
        JsonObject json = new JsonObject();

        Map<String, Long> map = batches.stream()
                .filter(e -> e.getCreationDate() != null)
                .collect(Collectors
                        .groupingBy(e -> e.getCreationDate().toString(), Collectors.counting()));

        SortedSet<String> days = new TreeSet<>(map.keySet());
        for (String day : days) {
            jsonDays.add(day);
            jsonBatches.add(map.get(day));
        }

        json.add("days", jsonDays);
        json.add("batches", jsonBatches);

        return json.toString();
    }
}
