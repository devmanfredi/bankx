package com.xcar.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankslipListDTO {
    private String id;
    private String due_date;
    private String total_in_cents;
    private String customer;
}
