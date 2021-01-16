package ru.mileev.chocofactory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double chocolateTemperature;
    private Double chocolateStirringSpeed;
    private Double chocolateServingSize;
    private Double creamWhippingTime;
    private Double creamWhippingSpeed;
    private String fillerType;
    private String fillerConsistency;
    private Double fillerWeight;
    private Double nutsWeight;
    private String nutsGrindingType;
    private String packagingType;
    private LocalDate creationDate;
    private Boolean formed;
}
