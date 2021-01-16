package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.mileev.chocofactory.domain.Report;
import ru.mileev.chocofactory.domain.User;
import ru.mileev.chocofactory.services.ReportService;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import static com.google.common.base.Strings.isNullOrEmpty;

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


    @SneakyThrows
    @PostMapping
    public String addReport(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam String startOfPeriod,
            @RequestParam String endOfPeriod,
            Model model) {

        Report report = new Report(null, LocalDate.parse(startOfPeriod),
                LocalDate.parse(endOfPeriod), user, file.getBytes());

        if (!isNullOrEmpty(file.getOriginalFilename())) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                log.info("Directory created: {}", uploadDir.mkdir());
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
        }

        service.create(report);
        model.addAttribute("reports", service.findAll());
        return "report";
    }
}
