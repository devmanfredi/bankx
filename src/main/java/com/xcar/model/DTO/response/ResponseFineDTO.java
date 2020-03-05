package com.xcar.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseFineDTO {
    private String message;
    private String status;
    private BankslipWithFine bankslip;
}
