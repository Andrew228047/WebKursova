package com.example.medicalwebapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Illnes")
public class Illnes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer illnesId; // Primary key

    private String illName; // Назва хвороби

    private String illDescription; // Опис хвороби

    private LocalDate patientRegistration; // Дата реєстрації пацієнта

    // Геттери та сеттери

    public Integer getIllnesId() {
        return illnesId;
    }

    public void setIllnesId(Integer illnesId) {
        this.illnesId = illnesId;
    }

    public String getIllName() {
        return illName;
    }

    public void setIllName(String illName) {
        this.illName = illName;
    }

    public String getIllDescription() {
        return illDescription;
    }

    public void setIllDescription(String illDescription) {
        this.illDescription = illDescription;
    }

    public LocalDate getPatientRegistration() {
        return patientRegistration;
    }

    public void setPatientRegistration(LocalDate patientRegistration) {
        this.patientRegistration = patientRegistration;
    }


}
