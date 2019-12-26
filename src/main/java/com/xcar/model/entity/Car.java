package com.xcar.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @OneToOne
    private ModelCar model;

    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    @NotNull
    private String chassis;

    @NotNull
    @OneToOne
    private CategoryCar category;

    @Column
    private String plate;

    @Column
    private BigDecimal price;

    @NotNull
    @OneToOne
    private Color color;
}
