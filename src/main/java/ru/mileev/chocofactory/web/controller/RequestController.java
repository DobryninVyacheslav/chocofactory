package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mileev.chocofactory.domain.Request;
import ru.mileev.chocofactory.domain.User;
import ru.mileev.chocofactory.services.RequestService;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService service;

    @GetMapping
    public String findByIngredients(@RequestParam(required = false) String ingredients,
                                    Model model) {

        model.addAttribute("requests", service.findByIngredients(ingredients));
        model.addAttribute("ingredients", ingredients);

        return "request";
    }

    @SneakyThrows
    @PostMapping
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam String ingredients,
            @RequestParam Integer quantity,
            @RequestParam String date,
            Model model) {

        service.create(new Request(ingredients, quantity, LocalDate.parse(date), user));
        model.addAttribute("requests", service.readAll());

        return "request";
    }
}
