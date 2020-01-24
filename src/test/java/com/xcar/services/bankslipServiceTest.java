package com.xcar.services;

import com.xcar.builders.BankslipBuilder;
import com.xcar.model.entity.Bankslip;
import com.xcar.repository.BankslipRepository;
import com.xcar.service.BankslipService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class bankslipServiceTest {

    @MockBean
    BankslipRepository bkRepository;
    @Autowired
    BankslipService bkService;

    @Test
    public void dadoUmBoleto_quandoEstiverPreenchido_entaoSalvar() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        Mockito.when(bkRepository.save(bankslip)).thenReturn(bankslip);
        Bankslip result = (Bankslip) bkService.save(bankslip);
        assertThat(result, Matchers.equalTo(bankslip));
    }

    @Test
    public void dadoBoletosExistentes_quandoHouverCadastros_entaoRetornarTodos() {
        List<Bankslip> bankslips = new ArrayList<>();
        bankslips = BankslipBuilder.bankslipList().getBankslipList();
        Mockito.when(bkRepository.findAll()).thenReturn(bankslips);
        List<Bankslip> result = bkService.findAll();
        assertThat(result, Matchers.equalTo(bankslips));
    }

    @Test
    public void dadoUmId_quandoExistir_entaoRetornarDetalhes() throws Throwable {
        Bankslip bankslip = new Bankslip();
        String uuid = UUID.randomUUID().toString();
        bankslip = BankslipBuilder.bankslip(uuid).build();
        Mockito.when(bkRepository.findById(bankslip.getId())).thenReturn(Optional.of(bankslip));
        Bankslip result = (Bankslip) bkService.findById(bankslip.getId()).orElseThrow(Exception::new);
        assertThat(result.getId(), Matchers.equalTo(bankslip.getId()));
    }

    @Test
    public void dadoBoleto_quandoNaoHouverStatus_entaoNaoSalvar() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        bankslip.setStatus(null);
        Mockito.when(bkRepository.save(bankslip)).thenReturn(null);
        Bankslip result = (Bankslip) bkService.save(bankslip);
        assertThat(result, Matchers.equalTo(null));
    }

    @Test
    public void dadoBoleto_quandoNaoHouverData_entaoNaoSalvar() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        bankslip.setDue_date(null);
        Mockito.when(bkRepository.save(bankslip)).thenReturn(null);
        Bankslip result = (Bankslip) bkService.save(bankslip);
        assertThat(result, Matchers.equalTo(null));
    }

    @Test
    public void dadoBoleto_quandoNaoHouveCustomer_entaoNaoSalvar() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        bankslip.setCustomer(null);
        Mockito.when(bkRepository.save(bankslip)).thenReturn(null);
        Bankslip result = (Bankslip) bkService.save(bankslip);
        assertThat(result, Matchers.equalTo(null));
    }

    @Test
    public void dadoBoleto_quandoNaoHouveValor_entaoNaoSalvar() {
        Bankslip bankslip = new Bankslip();
        bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        bankslip.setTotal_in_cents(null);
        Mockito.when(bkRepository.save(bankslip)).thenReturn(null);
        Bankslip result = (Bankslip) bkService.save(bankslip);
        assertThat(result, Matchers.equalTo(null));
    }


}
