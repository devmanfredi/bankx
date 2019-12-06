package com.xcar.controller;

import com.xcar.exception.ApiError;
import com.xcar.mapper.BankslipMapper;
import com.xcar.model.DTO.BankslipDTO;
import com.xcar.model.entity.Bankslip;
import com.xcar.service.BankslipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = {"Billets"}, description = "Endpoint para criação de boletos.")
public class BankslipController {

    private BankslipService bkService;
    private BankslipMapper bkMapper;

    @ApiOperation(
            value = "Cria um Boleto",
            notes = "Método utilizado para criar um Boleto"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Boleto criado", response = BankslipDTO.class),
            @ApiResponse(code = 400, message = "Requisão mal formatada", response = ApiError.class),
            @ApiResponse(code = 500, message = "Erro na apo", response = ApiError.class)
    })
    @PostMapping(value = "/bankslips")
    private BankslipDTO save(@RequestBody BankslipDTO bankslipDTO){
        final Bankslip entity = bkMapper.toEntity(bankslipDTO);
        bkService.save(entity);
        return bkMapper.toDTO(entity);
    }


}
