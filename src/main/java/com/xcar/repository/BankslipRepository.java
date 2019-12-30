package com.xcar.repository;

import com.xcar.model.entity.Bankslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, UUID> {

}
