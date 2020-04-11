package com.xcar.service;

import com.xcar.model.entity.Bankslip;
import com.xcar.model.enums.Status;
import com.xcar.repository.BankslipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BankslipService extends AbstractService {
    public BankslipService(JpaRepository repository) {
        super(repository);
    }

    @Autowired
    private BankslipRepository bkRepository;

    private static LocalDate DUO_DATE_FROM_CALCULATE = null;
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 10;
    private static final String FIRST_PERCENTAGE = "0.005";
    private static final String SECOND_PERCENTAGE = "0.01";

    public BigDecimal fineCalculate(Bankslip bankslip) throws Exception {
        if (bankslip == null) {
            //TODO:Fazer classe de exception
            throw new Exception();
        }
        BigDecimal fine = BigDecimal.ZERO;
        if(bankslip.getStatus().equals(Status.PENDING)){
            DUO_DATE_FROM_CALCULATE = bankslip.getDue_date();
            BigDecimal percentage = getFinePercentage();
            fine = bankslip.getTotal_in_cents().multiply(percentage);
        }
        return fine;
    }
    private BigDecimal getFinePercentage() {
        BigDecimal percentage = BigDecimal.ZERO;
        Long daysBetween = getDaysBetweenDueDateAndToday();
        if(daysBetween >= FIRST_DAY && daysBetween <= LAST_DAY){
            percentage = new BigDecimal(FIRST_PERCENTAGE);
        } else if(daysBetween > LAST_DAY){
            percentage = new BigDecimal(SECOND_PERCENTAGE);
        }
        return percentage;
    }

    private Long getDaysBetweenDueDateAndToday() {
        LocalDate now = LocalDate.now();
        Calendar calendar = Calendar.getInstance();
        //return DAYS.between(DUO_DATE_FROM_CALCULATE = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),now);
        return DAYS.between(DUO_DATE_FROM_CALCULATE.atStartOfDay(ZoneId.systemDefault()).toLocalDate(), now);
    }

    public Bankslip changeStatus(Optional<Bankslip> billet) {
        if (Status.PENDING.equals(billet.get().getStatus())) {
            bkRepository.updateBankslipStatusById(billet.get().getId(), Status.PAID);
            billet.get().setStatus(Status.PAID);
        } else if (Status.PAID.equals(billet.get().getStatus())) {
            bkRepository.updateBankslipStatusById(billet.get().getId(), Status.CANCELED);
            billet.get().setStatus(Status.CANCELED);
        } else {
            billet.get().setStatus(Status.CANCELED);
        }
        return billet.get();
    }
}
