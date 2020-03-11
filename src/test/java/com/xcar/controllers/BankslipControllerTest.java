package com.xcar.controllers;

import com.xcar.builders.BankslipBuilder;
import com.xcar.mapper.BankslipMapper;
import com.xcar.model.DTO.response.BankslipListDTO;
import com.xcar.model.DTO.response.ResponseDTO;
import com.xcar.model.DTO.response.ResponseFineDTO;
import com.xcar.model.entity.Bankslip;
import com.xcar.repository.BankslipRepository;
import com.xcar.service.BankslipService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.xcar.util.TestUtil.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BankslipControllerTest {

    private static String URI = "/rest/bankslips";

    @Autowired
    private BankslipService bkService;

    @MockBean
    private BankslipRepository bkRepository;

    @Autowired
    private BankslipMapper bkMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void dadoBoleto_quandoSalvar_entaoDeveRetornarBoleto() throws Exception {
        Bankslip bankslip = BankslipBuilder.bankslip(UUID.randomUUID().toString()).build();
        ResponseDTO bankslipDTO = bkMapper.toDTO(bankslip);
        Mockito.when(bkRepository.save(bankslip)).thenReturn(bankslip);

        ResultActions boletoSalvo = mvc.perform(post("/rest/bankslips")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(bankslipDTO)))
                .andExpect(status().isOk());
        boletoSalvo.andExpect(jsonPath("$.status", Matchers.is(bankslip.getStatus().toString())));
        boletoSalvo.andExpect(jsonPath("$.customer", Matchers.is(bankslip.getCustomer())));
    }

    @Test
    public void dadoBoleto_quandoBuscarPorId_entaoRetornarDetalhes() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Bankslip build = BankslipBuilder.bankslip(uuid).build();
        ResponseFineDTO bankslip = bkMapper.toDtoFine(build);
        Mockito.when(bkRepository.findById(bankslip.getBankslip().getId())).thenReturn(Optional.ofNullable(build));

        ResultActions boleto = mvc.perform(get("/rest/bankslips/" + uuid)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(bankslip)))
                .andExpect(status().isOk());
        boleto.andExpect(jsonPath("$.id", Matchers.is(bankslip.getBankslip().getId())));
    }

    @Test
    public void quandoBuscarLista_entaoRetornarLista() throws Exception {
        List<Bankslip> bankslips = BankslipBuilder.bankslipList().getBankslipList();
        List<BankslipListDTO> bankslipListDTOS = bkMapper.toListBankslipDTO(bankslips);
        Mockito.when(bkRepository.findAll()).thenReturn(bankslips);

        ResultActions result = mvc.perform(get("/rest/bankslips")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(bankslipListDTOS)))
                .andExpect(status().isOk());
        result.andExpect((jsonPath("$[0].id", Matchers.is(bankslipListDTOS.get(0).getId()))));
    }
}
