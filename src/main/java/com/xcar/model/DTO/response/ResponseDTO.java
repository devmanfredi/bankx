package com.xcar.model.DTO.response;

import com.xcar.model.DTO.request.BankslipDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private String message;
    private String status;
    private BankslipDTO bankslip;
}
