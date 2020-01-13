package com.xcar.builders;

import com.xcar.model.entity.Bankslip;
import com.xcar.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BankslipBuilder {

    private Bankslip bankslip;

    public static BankslipBuilder admin(){
        BankslipBuilder builder = new BankslipBuilder();
        builder.bankslip = Bankslip.builder()
               .id(UUID.randomUUID())
               .customer("Admmin")
               .due_date(LocalDate.now())
               .status(Status.PAID)
               .total_in_cents(BigDecimal.valueOf(25000))
               .build();
        return builder;
    }

    public static BankslipBuilder bankslip(){
        BankslipBuilder builder = new BankslipBuilder();
        builder.bankslip = Bankslip.builder()
               .id(UUID.randomUUID())
               .customer("João")
               .due_date(LocalDate.now())
               .status(Status.PENDING)
               .total_in_cents(BigDecimal.valueOf(10000))
               .build();
        return builder;
    }

    public Bankslip build(){
        return bankslip;
    }
}
