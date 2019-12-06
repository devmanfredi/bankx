package com.xcar.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BankslipService extends AbstractService{
    public BankslipService(JpaRepository repository) {
        super(repository);
    }
}
