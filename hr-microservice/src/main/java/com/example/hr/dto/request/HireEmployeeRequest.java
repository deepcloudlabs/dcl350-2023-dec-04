package com.example.hr.dto.request;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HireEmployeeRequest {
	@TcKimlikNo
    private String identity;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @DecimalMin(value = "12000.0")
    private double salary;
    @NotNull
    private FiatCurrency currency;
    @Iban
    private String iban;
    private Department[] departments;
    private JobStyle jobStyle;
    private int birthYear;
    private String photo;
}
