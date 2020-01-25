package com.xcar.controllers;

import com.xcar.mapper.BankslipMapper;
import com.xcar.repository.BankslipRepository;
import com.xcar.service.BankslipService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

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


}
