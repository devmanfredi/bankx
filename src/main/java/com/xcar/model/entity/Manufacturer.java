package com.xcar.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private LocalDate foundationDate;

    @Column
    @Size(max = 50)
    private String Owner;

    //TODO:CRIAR TABELAS DE PAÍS/ESTADO/CIDADE
    @Column
    @Size(max = 100)
    private String headOffice;//SEDE DA EMPRESA

}
