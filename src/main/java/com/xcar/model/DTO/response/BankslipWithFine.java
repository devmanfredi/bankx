package com.xcar.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankslipWithFine {
    private String id;
    private String due_date;
    private String total_in_cents;
    private String customer;
    private String fine;
    private String status;
}
