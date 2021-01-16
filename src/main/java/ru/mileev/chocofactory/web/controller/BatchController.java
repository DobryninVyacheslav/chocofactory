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
        model.addAttribute("batches", batches);
        model.addAttribute("batch", new Batch());

        return "batch";
    }

    @GetMapping("/{id}")
    public String getBatch(@PathVariable Long id,
                           Model model) {

        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();
        model.addAttribute("batches", batches);
        Batch batch = service.findById(id);
        model.addAttribute("batch", batch);

        return "batch";
    }

    @PostMapping("/save/{id}")
    public String updateBatch(@PathVariable Long id,
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

    @PostMapping("/save")
    public String createBatch(
            Double chocolateTemperature,
            Double chocolateStirringSpeed,
            Double chocolateServingSize,
            Model model) {


        Batch batch = service.save(Batch.builder()
                .chocolateTemperature(chocolateTemperature)
                .chocolateStirringSpeed(chocolateStirringSpeed)
                .chocolateServingSize(chocolateServingSize)
                .build());
        model.addAttribute("batch", batch);
        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();
        model.addAttribute("batches", batches);

        return "batch";
    }
}
