package com.xcar.model.entity;

import com.xcar.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bankslip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime due_date;

    @NotNull
    private BigDecimal total_in_cents;

    @NotNull
    private String customer;

    @Transient
    private BigDecimal fine;

    @NotNull
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
