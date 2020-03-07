package com.xcar.repository;

import com.xcar.model.entity.Bankslip;
import com.xcar.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, String> {
    @Transactional
    @Modifying
    @Query("UPDATE Bankslip b SET b.status = :status WHERE b.id = :id")
    void updateBankslipStatusById(@Param("id") String id, @Param("status") Status status);

    @Transactional
    @Modifying
    @Query("UPDATE Bankslip b SET b.status = :status WHERE b.id = :id")
    void pay(@Param("id") String id, @Param("status") Status status);

}
