package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/main")
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam String ingredients,
            @RequestParam Integer quantity,
            @RequestParam String date,
            Model model) {
        Request request = new Request(null, ingredients, quantity, LocalDate.parse(date), user);
        service.create(request);
        model.addAttribute("requests", service.readAll());
        return "main";
    }
}
