package com.example.medicalwebapp.repository;

import com.example.medicalwebapp.model.Patient; // Замініть на правильний шлях до вашого класу моделі
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import com.example.medicalwebapp.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("SELECT p FROM Patient p WHERE " +
            "(:name IS NULL OR p.patientName LIKE %:name%) AND " +
            "(:illName IS NULL OR p.patientIll.illName LIKE %:illName%) AND " +
            "(:dob IS NULL OR p.dateOfBirth = :dob) AND " +
            "(:regDate IS NULL OR p.patientIll.patientRegistration = :regDate)")
    List<Patient> findByFilters(@Param("name") String name,
                                @Param("illName") String illName,
                                @Param("dob") LocalDate dob,
                                @Param("regDate") LocalDate regDate);
}
