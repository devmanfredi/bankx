package com.xcar.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xcar.model.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankslipDTO {

    @ApiModelProperty(value = "Data do boleto", example = "2019-12-31", required = true)
    @NotNull
    private LocalDate due_date;

    @ApiModelProperty(value = "Valor em centavos", example = "10000", required = true)
    @NotNull
    private BigDecimal total_in_cents;

    @ApiModelProperty(value = "Customer", example = "João da XCar", required = true)
    @Size(min = 1, max = 120)
    @NotNull
    private String customer;

    @ApiModelProperty(value = "Status do Boleto", position = 4, example = "PENDING", allowableValues = "PENDING, PAID, CANCELED", required = true)
    @NotNull
    private String status;
}
