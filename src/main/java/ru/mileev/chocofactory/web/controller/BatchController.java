package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.services.BatchService;

import java.util.List;

@Controller
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService service;

    @GetMapping
    public String getPage(Model model) {

        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute("batch", new Batch());
        model.addAttribute("batches", batches);

        return "batch";
    }

    @GetMapping("/{id}")
    public String getBatch(@PathVariable Long id,
                           Model model) {

        Batch batch = service.findById(id);
        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute("batch", batch);
        model.addAttribute("batches", batches);

        return "batch";
    }

    @PostMapping("/save-chocolate/{id}")
    public String updateBatchWithChocolate(@PathVariable Long id,
                              Double chocolateTemperature,
                              Double chocolateStirringSpeed,
                              Double chocolateServingSize,
                              Model model) {

        Batch batch = service.findById(id);
        batch.setChocolateTemperature(chocolateTemperature);
        batch.setChocolateStirringSpeed(chocolateStirringSpeed);
        batch.setChocolateServingSize(chocolateServingSize);

        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute("batches", batches);
        model.addAttribute("batch", batch);

        return "batch";
    }

    @PostMapping("/save-chocolate")
    public String createBatchWithChocolate(
            Double chocolateTemperature,
            Double chocolateStirringSpeed,
            Double chocolateServingSize,
            Model model) {


        Batch batch = service.save(Batch.builder()
                .chocolateTemperature(chocolateTemperature)
                .chocolateStirringSpeed(chocolateStirringSpeed)
                .chocolateServingSize(chocolateServingSize)
                .build());
        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute("batch", batch);
        model.addAttribute("batches", batches);

        return "batch";
    }

    @PostMapping("/save-cream/{id}")
    public String updateBatchWithCream(@PathVariable Long id,
                              Double creamWhippingTime,
                              Double creamWhippingSpeed,
                              Model model) {

        Batch batch = service.findById(id);
        batch.setCreamWhippingTime(creamWhippingTime);
        batch.setCreamWhippingSpeed(creamWhippingSpeed);

        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute("batches", batches);
        model.addAttribute("batch", batch);

        return "batch";
    }

    @PostMapping("/save-cream")
    public String createBatchWithCream(
            Double creamWhippingTime,
            Double creamWhippingSpeed,
            Model model) {


        Batch batch = service.save(Batch.builder()
                .creamWhippingTime(creamWhippingTime)
                .creamWhippingSpeed(creamWhippingSpeed)
                .build());
        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute("batch", batch);
        model.addAttribute("batches", batches);

        return "batch";
    }
}
