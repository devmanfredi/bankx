package com.xcar.mapper;

import com.xcar.model.DTO.BankslipDTO;
import com.xcar.model.entity.Bankslip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankslipMapper {
    @Mappings({
            @Mapping(source = "due_date", target = "due_date", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "total_in_cents", target = "total_in_cents"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status")
    })
    BankslipDTO toDTO(Bankslip bankslip);
    List<BankslipDTO> toDTOlList(List<Bankslip> bankslips);


    @Mappings({
            @Mapping(source = "due_date", target = "due_date", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "total_in_cents", target = "total_in_cents"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "status", target = "status")
    })
    Bankslip toEntity(BankslipDTO bankslipDTO);


}
