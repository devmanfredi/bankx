package com.xcar.model.DTO.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private String total_in_cents;

    @ApiModelProperty(value = "Customer", example = "João da XCar", required = true)
    @NotNull
    private String customer;

    @ApiModelProperty(value = "Status do Boleto", position = 4, example = "PENDING", allowableValues = "PENDING, PAID, CANCELED", required = true)
    @NotNull
    private String status;
}
