package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.services.BatchService;
import ru.mileev.chocofactory.services.ReportService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;
    private final BatchService batchService;

    @GetMapping
    public String get() {
        return "report";
    }

    @GetMapping("/chart-data")
    @ResponseBody
    public String getChartData(@RequestParam(defaultValue = "1970-01-01") String startOfPeriod,
                               @RequestParam(defaultValue = "3000-01-01") String endOfPeriod) {
        return service.prepareReportData(LocalDate.parse(startOfPeriod), LocalDate.parse(endOfPeriod));
    }

    @GetMapping("/table-data")
    @ResponseBody
    public List<Batch> getTableData(@RequestParam(defaultValue = "1970-01-01") String startOfPeriod,
                                    @RequestParam(defaultValue = "3000-01-01") String endOfPeriod) {
        return batchService.findAllByCreationDateBetween(LocalDate.parse(startOfPeriod),
                LocalDate.parse(endOfPeriod));
    }
}
