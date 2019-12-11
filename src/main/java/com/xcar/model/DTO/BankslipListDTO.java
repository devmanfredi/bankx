package com.xcar.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankslipListDTO{
    private Long id;
    private LocalDate due_date;
    private BigDecimal total_in_cents;
    private String customer;
}
