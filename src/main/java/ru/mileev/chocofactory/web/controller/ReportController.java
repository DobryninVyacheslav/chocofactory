package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mileev.chocofactory.services.ReportService;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String get(@RequestParam(required = false) String startOfPeriod,
                      @RequestParam(required = false) String endOfPeriod,
                      Model model) {

        model.addAttribute("reports", service.findAll());

        return "report";
    }

    @GetMapping("/chart-data")
    @ResponseBody
    public String getChartData(@RequestParam(defaultValue = "1970-01-01") String startOfPeriod,
                               @RequestParam(defaultValue = "3000-01-01") String endOfPeriod) {
        return service.prepareReportData(LocalDate.parse(startOfPeriod), LocalDate.parse(endOfPeriod));
    }
}
