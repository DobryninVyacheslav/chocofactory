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
import ru.mileev.chocofactory.domain.Request;
import ru.mileev.chocofactory.domain.User;
import ru.mileev.chocofactory.services.RequestService;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Strings.isNullOrEmpty;

@Slf4j
@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService service;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String home(@RequestParam(required = false) String ingredients,
                       Model model) {

        List<Request> requestsByIngredients;

        if (ingredients != null && !ingredients.isEmpty()) {
            requestsByIngredients = service.readAllByIngredients(ingredients);
        } else {
            requestsByIngredients = service.readAll();
        }

        model.addAttribute("requests", requestsByIngredients);
        model.addAttribute("ingredients", ingredients);

        return "main";
    }

    @SneakyThrows
    @PostMapping
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam String ingredients,
            @RequestParam Integer quantity,
            @RequestParam String date,
            Model model) {
        Request request = new Request(ingredients, quantity, LocalDate.parse(date), user);

        if (file != null && !isNullOrEmpty(file.getOriginalFilename())) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                log.info("Directory created: {}",uploadDir.mkdir());
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            request.setFilename(resultFilename);
        }

        service.create(request);
        model.addAttribute("requests", service.readAll());
        return "main";
    }
}
