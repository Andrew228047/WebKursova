package com.example.medicalwebapp.service;

import com.example.medicalwebapp.model.Patient;
import com.example.medicalwebapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public List<Patient> filterPatients(String name, String illName, LocalDate dob, LocalDate regDate) {
        // Логіка фільтрації (можна створити методи в репозиторії)
        return patientRepository.findByFilters(name, illName, dob, regDate);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}
