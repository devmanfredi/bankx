package com.xcar.model.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseListDTO {
    private String status;
    private String message;
    List<BankslipListDTO> bankslips;
}
