package com.example.medicalwebapp.model;

import com.example.medicalwebapp.model.Illnes;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId; // Primary key

    private String patientName; // Ім'я пацієнта

    private LocalDate dateOfBirth; // Дата народження пацієнта

    @ManyToOne // Зв'язок з класом Illnes
    @JoinColumn(name = "patientIll", referencedColumnName = "illnesId") // Foreign key
    private Illnes patientIll; // Діагноз пацієнта

    // Геттери та сеттери

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Illnes getPatientIll() {
        return patientIll;
    }

    public void setPatientIll(Illnes patientIll) {
        this.patientIll = patientIll;
    }
}
