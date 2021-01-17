package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.domain.NotificationMessages;
import ru.mileev.chocofactory.services.BatchService;
import ru.mileev.chocofactory.services.NotificationService;

import java.util.List;

@Controller
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    public static final String BATCH = "batch";
    public static final String BATCHES = "batches";
    private final BatchService service;
    private final NotificationService notificationService;

    @GetMapping
    public String getPage(Model model) {

        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute(BATCH, new Batch());
        model.addAttribute(BATCHES, batches);

        return BATCH;
    }

    @GetMapping("/{id}")
    public String getBatch(@PathVariable Long id,
                           Model model) {

        Batch batch = service.findById(id);
        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, batches);

        return BATCH;
    }

    @PostMapping("/create")
    public String createBatch(Model model) {

        Batch batch = service.save(new Batch());
        List<Batch> batches = service.findAllByFormedNullOrFormedFalse();

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, batches);

        return BATCH;
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
        service.save(batch);

        notificationService.save(batch, NotificationMessages.CHOCOLATE_PREPARED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
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

        notificationService.save(batch, NotificationMessages.CHOCOLATE_PREPARED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-cream/{id}")
    public String updateBatchWithCream(@PathVariable Long id,
                                       Double creamWhippingTime,
                                       Double creamWhippingSpeed,
                                       Model model) {

        Batch batch = service.findById(id);
        batch.setCreamWhippingTime(creamWhippingTime);
        batch.setCreamWhippingSpeed(creamWhippingSpeed);
        service.save(batch);

        notificationService.save(batch, NotificationMessages.CREAM_WHIPPED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
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

        notificationService.save(batch, NotificationMessages.CREAM_WHIPPED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-filler/{id}")
    public String updateBatchWithFiller(@PathVariable Long id,
                                        String fillerType,
                                        String fillerConsistency,
                                        Double fillerWeight,
                                        Model model) {

        Batch batch = service.findById(id);
        batch.setFillerType(fillerType);
        batch.setFillerConsistency(fillerConsistency);
        batch.setFillerWeight(fillerWeight);
        service.save(batch);

        notificationService.save(batch, NotificationMessages.FILLER_EXPLORED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-filler")
    public String createBatchWithFiller(String fillerType,
                                        String fillerConsistency,
                                        Double fillerWeight,
                                        Model model) {

        Batch batch = service.save(Batch.builder()
                .fillerType(fillerType)
                .fillerConsistency(fillerConsistency)
                .fillerWeight(fillerWeight)
                .build());

        notificationService.save(batch, NotificationMessages.FILLER_EXPLORED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-nuts/{id}")
    public String updateBatchWithNuts(@PathVariable Long id,
                                      Double nutsWeight,
                                      String nutsGrindingType,
                                      Model model) {

        Batch batch = service.findById(id);
        batch.setNutsWeight(nutsWeight);
        batch.setNutsGrindingType(nutsGrindingType);
        service.save(batch);

        notificationService.save(batch, NotificationMessages.NUTS_CLEANED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-nuts")
    public String createBatchWithNuts(Double nutsWeight,
                                      String nutsGrindingType,
                                      Model model) {

        Batch batch = service.save(Batch.builder()
                .nutsWeight(nutsWeight)
                .nutsGrindingType(nutsGrindingType)
                .build());

        notificationService.save(batch, NotificationMessages.NUTS_CLEANED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-packaging/{id}")
    public String updateBatchWithPackaging(@PathVariable Long id,
                                           String packagingType,
                                           Model model) {

        Batch batch = service.findById(id);
        batch.setPackagingType(packagingType);
        service.save(batch);

        notificationService.save(batch, NotificationMessages.BATCH_PACKED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }

    @PostMapping("/save-packaging")
    public String createBatchWithPackaging(String packagingType,
                                           Model model) {

        Batch batch = service.save(Batch.builder()
                .packagingType(packagingType)
                .build());

        notificationService.save(batch, NotificationMessages.BATCH_PACKED);

        model.addAttribute(BATCH, batch);
        model.addAttribute(BATCHES, service.findAllByFormedNullOrFormedFalse());

        return BATCH;
    }
}
