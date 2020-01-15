package com.xcar;

import com.xcar.builders.BankslipBuilder;
import com.xcar.model.entity.Bankslip;
import com.xcar.repository.BankslipRepository;
import com.xcar.service.BankslipService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class bankslipServiceTest {

    @Mock
    BankslipRepository bkRepository;
    @Autowired
    BankslipService bkService;

    @Test
    public void dadoUmBoleto_quandoEstiverPreenchido_entaoSalvar() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(null).build();
        Mockito.when(bkRepository.save(bankslip)).thenReturn(bankslip);
        Bankslip result = (Bankslip) bkService.save(bankslip);
        assertThat(result, Matchers.equalTo(bankslip));
    }

    @Test
    public void dadoBoletosExistentes_quandoHouverCadastros_entaoRetornarTodos() {
        List<Bankslip> bankslips = new ArrayList<>();
        bankslips = bkRepository.findAll();
        Mockito.when(bkRepository.findAll()).thenReturn(bankslips);
        List<Bankslip> returnBankslips = bkService.findAll();
        assertThat(returnBankslips, Matchers.equalTo(bankslips));
    }

    @Test
    public void dadoUmId_quandoExistir_entaoRetornarDetalhes() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(UUID.randomUUID()).build();
        Mockito.when(bkRepository.findById(bankslip.getId())).thenReturn(Optional.of(bankslip));
        Optional<Bankslip> result = bkService.findById(bankslip.getId());
        assertThat(bankslip.getId(), Matchers.equalTo(result.get().getId()));
    }

}
