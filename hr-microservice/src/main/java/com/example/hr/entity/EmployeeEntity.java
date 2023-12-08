package com.example.hr.entity;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="employees")
@Data
@EqualsAndHashCode(of = "identity")
public class EmployeeEntity {
	@TcKimlikNo
	@Id
    private String identity;
    @NotBlank
    @Column(name="fname")
    private String firstName;
    @NotBlank
    @Column(name="lname")
    private String lastName;
    private double salary;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FiatCurrency currency;
    @Iban
    private String iban;
    @ElementCollection
    private Department[] departments;
    @Enumerated(EnumType.ORDINAL)
    private JobStyle jobStyle;
    @Column(name="byear")
    private int birthYear;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] photo;

}
