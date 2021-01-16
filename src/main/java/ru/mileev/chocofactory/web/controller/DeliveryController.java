package ru.mileev.chocofactory.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mileev.chocofactory.domain.Batch;
import ru.mileev.chocofactory.domain.Delivery;
import ru.mileev.chocofactory.services.BatchService;
import ru.mileev.chocofactory.services.DeliveryService;

import java.time.LocalDate;

@Controller
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final BatchService batchService;
    private final DeliveryService deliveryService;

    @GetMapping("/{batchId}")
    public String getPage(@PathVariable("batchId") Batch batch, Model model) {

        model.addAttribute("batch", batch);

        return "delivery";
    }

    @PostMapping("/{batchId}")
    public String formBatchDelivery(@PathVariable("batchId") Batch batch,
                                    String order,
                                    String deliveryAddress,
                                    LocalDate deliveryDate,
                                    Model model) {

        batch.setFormed(true);
        batch.setCreationDate(LocalDate.now());
        batch = batchService.save(batch);

        deliveryService.save(new Delivery(null,order,deliveryAddress,deliveryDate));
        model.addAttribute("batch", batch);

        return "delivery";
    }

}
