package com.xcar.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePayDTO {
    private String status;
    private String message;
    private BankslipStatusPay bankslip;
}
