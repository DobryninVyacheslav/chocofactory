package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mileev.chocofactory.domain.Request;
import ru.mileev.chocofactory.domain.User;
import ru.mileev.chocofactory.services.RequestService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RequestService service;

    @GetMapping("/")
    public String request(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/main")
    public String home(Map<String, Object> model) {
        model.put("requests", service.readAll());
        return "main";
    }

    @PostMapping("/main")
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam String ingredients,
            @RequestParam Integer quantity,
            @RequestParam String date,
            Map<String, Object> model) {
        Request request = new Request(null, ingredients, quantity, LocalDate.parse(date));
        service.create(request);
        model.put("requests", service.readAll());
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String ingredients, Map<String, Object> model) {
        List<Request> requests;

        if (ingredients != null && !ingredients.isEmpty()) {
            requests = service.readAllByIngredients(ingredients);
        } else {
            requests = service.readAll();
        }

        model.put("requests", requests);

        return "main";
    }
}
