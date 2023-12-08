package com.example.hr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document(collection="employees")
public class EmployeeDocument {
	@TcKimlikNo
	@Id
    private String identity;
    @NotBlank
    @Field("fname")
    private String firstName;
    @NotBlank
    @Field("lname")
    private String lastName;
    @Field("maas")
    private double salary;
    @NotNull
    private FiatCurrency currency;
    @Iban
    @Indexed(unique = true)
    private String iban;
    private Department[] departments;
    private JobStyle jobStyle;
    private int birthYear;
    private String photo;
}
