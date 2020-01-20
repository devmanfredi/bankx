package com.xcar.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xcar.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bankslip {

    @Id
    private String id;

    @NotNull
    private LocalDate due_date;

    @NotNull
    private BigDecimal total_in_cents;

    @NotNull
    private String customer;

    @Transient
    private BigDecimal fine;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateAt;

}
