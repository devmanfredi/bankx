package com.xcar.model.DTO;

import com.xcar.model.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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

    @ApiModelProperty(value = "Status do Boleto", position = 4, example = "PENDING", allowableValues = "PENDING, PAID, CANCELED", required = true)
    @NotNull
    private Status status;
}
