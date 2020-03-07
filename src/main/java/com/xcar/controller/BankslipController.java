package com.xcar.controller;

import com.xcar.exception.ApiError;
import com.xcar.mapper.BankslipMapper;
import com.xcar.model.DTO.request.BankslipDTO;
import com.xcar.model.DTO.response.*;
import com.xcar.model.entity.Bankslip;
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
    private ResponseDTO save(@RequestBody BankslipDTO bankslipDTO) throws Exception {
        final Bankslip entity = bkMapper.toEntity(bankslipDTO);
        try {
            entity.setId(UUID.randomUUID().toString());
            bkService.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        //TODO:Melhorar o retorno através de um mapper
        return bkMapper.toDTO(entity);
    }

    @ApiOperation(
            value = "Retorna uma lista de boletos",
            notes = "Método utilizado para Listar todos os boletos"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
    })
    @GetMapping("/bankslips")
    private ResponseListDTO findAll(){
        List billets = bkService.findAll();
        if (billets.isEmpty()){
            return null;
        }
        ResponseListDTO responseListDTO = new ResponseListDTO();
        responseListDTO.setStatus("200");
        responseListDTO.setMessage("OK");
        responseListDTO.setBankslips(bkMapper.toListBankslipDTO(billets));
        return responseListDTO;
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
    @GetMapping("/bankslips/{id}")
    private ResponseFineDTO findById(@PathVariable String id) throws Exception {
        Optional<Bankslip> bankslip = bkService.findById(id);
        if (!bankslip.isPresent()) {
            throw new Exception("Boleto não encontrado!");
        }
        bankslip.get().setFine(bkService.fineCalculate(bankslip.get()));
        return (bankslip.isPresent()) ? bkMapper.toDtoFine(bankslip.get()) : null;
    }

    @PutMapping("/bankslips/{id}")
    private BankslipStatusPay changeStatus(@PathVariable String id, @RequestBody BankslipStatusPay bankslipStatusPay) throws Exception {
        Optional<Bankslip> billet = bkService.findById(id);
        if (!billet.isPresent()) {
            throw new Exception("Boleto não encontrado!");
        }
        Bankslip bankslip = bkService.changeStatus(billet, bankslipStatusPay);
        return bkMapper.toStatusBeforePut(bankslip);
    }
    @PutMapping("/bankslips/pay/{id}")
    private ResponsePayDTO payment(@PathVariable String id) throws Exception {
        Optional<Bankslip> billet = bkService.findById(id);
        if (!billet.isPresent()) {
            throw new Exception("Boleto não encontrado!");
        }
        Bankslip bankslipPaiding = bkService.pay(billet.get());
        return bkMapper.toDTOPay(bankslipPaiding);
    }

}
