package com.xcar.model.DTO.response;

import com.xcar.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankslipStatusPay {
    private Status status;
}
