package com.xcar.builders;

import com.xcar.model.entity.Bankslip;
import com.xcar.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankslipBuilder {

    private Bankslip bankslip;
    private List<Bankslip> bankslipList = new ArrayList<>();

    public static BankslipBuilder admin(){
        BankslipBuilder builder = new BankslipBuilder();
        builder.bankslip = Bankslip.builder()
                .id(UUID.randomUUID().toString())
               .customer("Admmin")
               .due_date(LocalDate.now())
               .status(Status.PAID)
               .total_in_cents(BigDecimal.valueOf(25000))
               .build();
        return builder;
    }

    public static BankslipBuilder bankslip(String uuid) {
        BankslipBuilder builder = new BankslipBuilder();
        builder.bankslip = Bankslip.builder()
                .id(uuid)
                .customer("TDD is necessary")
                .due_date(LocalDate.now())
                .status(Status.PENDING)
                .total_in_cents(BigDecimal.valueOf(10000))
                .build();
        return builder;
    }

    public static BankslipBuilder bankslipList() {
        BankslipBuilder builder = new BankslipBuilder();
        for (int i = 0; i < 5; i++) {
            builder.bankslipList.add(bankslip(UUID.randomUUID().toString()).build());
        }
        return builder;
    }

    public Bankslip build() {
        return bankslip;
    }

    public List<Bankslip> getBankslipList() {
        return bankslipList;
    }
}
