package com.example.medicalwebapp.controller;

import com.example.medicalwebapp.model.Patient;
import com.example.medicalwebapp.service.PatientService;
import com.example.medicalwebapp.service.IllnesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:63343")
public class ViewController {
//
//    private final PatientService patientService;
//    private final IllnesService illnessService;

//    // Інжектуємо сервіси через конструктор
//    @Autowired
//    public ViewController(PatientService patientService, IllnesService illnessService) {
//        this.patientService = patientService;
//        this.illnessService = illnessService;
//    }

//    @GetMapping("/patients/view")
//    public String getPatients(Model model) {
//        List<Patient> patients = patientService.getAllPatients();
//        model.addAttribute("patients", patients);
//        return "patient_list";  // Повертаємо шаблон для відображення пацієнтів
//    }
//
//    @GetMapping("/patients/personnel")
//    public String getPersonnelCount(@RequestParam("date") LocalDate date, Model model) {
//        long count = patientService.countPatientsByDateOfBirthBetween(date, date);
//        model.addAttribute("count", count);
//        return "diagnosis_count";  // Повертаємо шаблон для діагнозів
//    }

//    @GetMapping("/patients/count")
//    public String getCountByDates(@RequestParam("startDate") LocalDate startDate,
//                                  @RequestParam("endDate") LocalDate endDate, Model model) {
//        long count = patientService.countPatientsByDateOfBirthBetween(startDate, endDate);
//        model.addAttribute("count", count);
//        return "date_count";  // Повертаємо шаблон для підрахунку по датах
//    }

//    @GetMapping("/patients/form")
//    public String showPatientForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
//        System.out.println("++++++++++++++COMPLITED++++++++++++++");
//        Patient patient = id == null ? new Patient() : patientService.getPatientById(id);
//        model.addAttribute("patient", patient);
//        model.addAttribute("illnesses", illnessService.getAllIllneses());  // Додаємо всі діагнози
//        System.out.println("======================");
//        System.out.println(illnessService.getAllIllneses());
//        return "patient_form";  // Повертаємо форму для створення/редагування пацієнта
//
//    }
}
