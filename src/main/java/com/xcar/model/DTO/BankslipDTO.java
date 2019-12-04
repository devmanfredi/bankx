package com.xcar.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankslipDTO {
    private LocalDateTime due_date;
    private BigDecimal total_in_cents;
    private String customer;
    private String status;
}
