package com.xcar.mapper;

import com.xcar.model.DTO.request.BankslipDTO;
import com.xcar.model.DTO.response.*;
import com.xcar.model.entity.Bankslip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankslipMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "due_date", target = "due_date"),
            @Mapping(source = "total_in_cents", target = "total_in_cents"),
            @Mapping(source = "customer", target = "customer")
    })
    BankslipListDTO toListDto(Bankslip bankslip);

    List<BankslipListDTO> toListBankslipDTO(List<Bankslip> bankslips);

    @Mappings({
            @Mapping(expression = "java(\"200\")", target = "status"),
            @Mapping(expression = "java(\"OK\")", target = "message"),
            @Mapping(source = "id", target = "bankslip.id"),
            @Mapping(source = "due_date", target = "bankslip.due_date"),
            @Mapping(source = "total_in_cents", target = "bankslip.total_in_cents"),
            @Mapping(source = "customer", target = "bankslip.customer"),
            @Mapping(source = "fine", target = "bankslip.fine"),
            @Mapping(source = "status", target = "bankslip.status")
    })
    ResponseFineDTO toDtoFine(Bankslip bankslip);

    @Mappings({
            @Mapping(source = "due_date", target = "due_date"),
            @Mapping(source = "total_in_cents", target = "total_in_cents"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status"),
            @Mapping(target = "createdAt", source = ""),
            @Mapping(target = "fine", source = ""),
            @Mapping(target = "id", source = ""),
            @Mapping(target = "updateAt", source = "")
    })
    Bankslip toEntity(BankslipDTO bankslipDTO);

    @Mappings({
            @Mapping(expression = "java(\"201\")", target = "status"),
            @Mapping(expression = "java(\"Bankslip created\")", target = "message"),
            @Mapping(source = "due_date", target = "bankslip.due_date"),
            @Mapping(source = "total_in_cents", target = "bankslip.total_in_cents"),
            @Mapping(source = "customer", target = "bankslip.customer"),
            @Mapping(source = "status", target = "bankslip.status")
    })
    ResponseDTO toDTO(Bankslip bankslip);

    @Mappings({
            @Mapping(source = "status", target = "status")
    })
    BankslipStatusPay toStatusBeforePut(Bankslip bankslip);

    @Mappings({
            @Mapping(target = "status", expression = "java(\"200\")"),
            @Mapping(target = "message", expression = "java(\"Bankslip paid\")"),
            @Mapping(target = "bankslip.status", source = "status")
    })
    ResponsePayDTO toDTOPay(Bankslip bankslip);

}
