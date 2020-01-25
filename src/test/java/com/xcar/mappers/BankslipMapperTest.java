package com.xcar.mappers;

import com.xcar.builders.BankslipBuilder;
import com.xcar.mapper.BankslipMapper;
import com.xcar.model.DTO.BankslipDTO;
import com.xcar.model.DTO.BankslipListDTO;
import com.xcar.model.entity.Bankslip;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertThat;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BankslipMapperTest {

    @Autowired
    BankslipMapper bkmaper;

    @Test
    public void dadoBoleto_quandoExececutarMapper_entaoRetornarBankslipDTO() {
        Bankslip bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        BankslipListDTO bkDTO = bkmaper.toListDto(bankslip);
        assertThat(bkDTO, Matchers.notNullValue());
        assertThat(bkDTO.getId(), Matchers.equalTo(bankslip.getId()));

    }

    @Test
    public void dadaUmaListaPadrao_quandoExecutarMapper_entaoRetornarListaDTO() {
        List<Bankslip> bankslips = BankslipBuilder.bankslipList().getBankslipList();
        List<BankslipListDTO> bankslipListDTOS = bkmaper.toListBankslipDTO(bankslips);
        assertThat(bankslipListDTOS, Matchers.equalTo(bkmaper.toListBankslipDTO(bankslips)));
    }

    @Test
    public void dadaUmaListaDTO_quandoExecutarMapper_entaoRetornarListaPadrao() {
        Bankslip bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        bankslip.setId(null);
        bankslip.setFine(null);
        bankslip.setCreatedAt(null);
        bankslip.setUpdateAt(null);
        BankslipDTO bkDTO = bkmaper.toDTO(bankslip);
        assertThat(bkmaper.toEntity(bkDTO), Matchers.equalTo(bankslip));
    }
}
