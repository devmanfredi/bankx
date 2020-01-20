package com.xcar.mapper;

import com.xcar.model.DTO.BankslipDTO;
import com.xcar.model.DTO.BankslipListDTO;
import com.xcar.model.DTO.response.BankslipWithFine;
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
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "due_date", target = "due_date"),
            @Mapping(source = "total_in_cents", target = "total_in_cents"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "fine", target = "fine"),
            @Mapping(source = "status", target = "status")
    })
    BankslipWithFine toDtoWithFine(Bankslip bankslip);

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
            @Mapping(source = "due_date", target = "due_date"),
            @Mapping(source = "total_in_cents", target = "total_in_cents"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status")
    })
    BankslipDTO toDTO(Bankslip bankslip);

}
