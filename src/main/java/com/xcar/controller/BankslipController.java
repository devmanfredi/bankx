package com.xcar.controller;

import com.xcar.exception.ApiError;
import com.xcar.mapper.BankslipMapper;
import com.xcar.model.DTO.BankslipDTO;
import com.xcar.model.DTO.BankslipListDTO;
import com.xcar.model.entity.Bankslip;
import com.xcar.model.enums.Status;
import com.xcar.service.BankslipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/rest")
@Api(tags = {"Billets"}, description = "Endpoint para criação de boletos.")
public class BankslipController {

    private BankslipService bkService;
    private BankslipMapper bkMapper;

    @ApiOperation(
            value = "Cria um Boleto",
            notes = "Método utilizado para criar um Boleto"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bankslip created", response = BankslipDTO.class),
            @ApiResponse(code = 400, message = "Bankslip not provided in the request body", response = ApiError.class),
            @ApiResponse(code = 422, message = " Invalid bankslip provided.The possible reasons are: ○ A field of the provided bankslip was null or with invalid values", response = ApiError.class)
    })
    @PostMapping(value = "/bankslips")
    private BankslipDTO save(@RequestBody BankslipDTO bankslipDTO) throws Exception {
        final Bankslip entity = bkMapper.toEntity(bankslipDTO);
        try {
            bkService.save(entity);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return bankslipDTO;
    }

    @ApiOperation(
            value = "Retorna uma lista de boletos",
            notes = "Método utilizado para Listar todos os boletos"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
    })
    @GetMapping("/bankslips")
    private List<BankslipListDTO> findAll(){
        List billets = bkService.findAll();
        if (billets.isEmpty()){
            return Collections.emptyList();
        }
        return bkMapper.toListBankslipDTO(billets);
    }

    @ApiOperation(
            value = "Detalhes de um Boleto",
            notes = "Método utilizado para ver os detalhes de um boleto"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = " Invalid id provided - it must be a valid UUID", response = ApiError.class),
            @ApiResponse(code = 404, message = " Bankslip not found with the specified id", response = ApiError.class)
    })
    @GetMapping("/{bankslipId}")
    private Bankslip findById(@PathVariable UUID bankslipId) throws Exception {
        Optional<Bankslip> bankslip = bkService.findById(bankslipId);
        bankslip.get().setFine(bkService.fineCalculate(bankslip.get()));
        return (bankslip.isPresent()) ? bankslip.get() : null;
    }

    @PutMapping("/bankslips/{id}")
    private BankslipDTO pay(@PathVariable String id, @RequestBody BankslipDTO dto) {
        return bkService.payBankslip(id, Status.PAID);
    }

}
