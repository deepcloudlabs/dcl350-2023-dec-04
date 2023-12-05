package com.example.hr.dto.response;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import lombok.Data;

@Data
public class HireEmployeeResponse {
    private String identity;
    private String firstName;
    private String lastName;
    private double salary;
    private FiatCurrency currency;
    private String iban;
    private Department[] departments;
    private JobStyle jobStyle;
    private int birthYear;
    private String photo;
}
