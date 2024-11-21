package com.example.medicalwebapp.controller;

import com.example.medicalwebapp.model.Illnes;
import com.example.medicalwebapp.service.IllnesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.medicalwebapp.model.Patient;
import com.example.medicalwebapp.service.PatientService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:63343")
@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private IllnesService illnesService;

    // Показати список пацієнтів
    @GetMapping
    public String getPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        System.out.println("Retrieved all patients: " + patients);
        return "mainTable"; // Зміна на назву вашої HTML сторінки
    }

    // Видалити пацієнта
    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") int id) {
        System.out.println("Deleting patient with ID: " + id);
        patientService.deleteById(id);
        return "redirect:/patients"; // Зміна редіректу на головну сторінку пацієнтів
    }

    // Редагування пацієнта
    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable("id") long id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        System.out.println("Editing patient: " + patient);
        return "editPatient"; // Окрема сторінка для редагування
    }

    // Оновлення пацієнта
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient updatedPatient) {
        System.out.println("Received request to update patient with ID: " + id + " with data: " + updatedPatient);

        Patient existingPatient = patientService.findById(id);
        if (existingPatient != null) {
            existingPatient.setPatientName(updatedPatient.getPatientName());
            existingPatient.getPatientIll().setIllName(updatedPatient.getPatientIll().getIllName());
            existingPatient.getPatientIll().setIllDescription(updatedPatient.getPatientIll().getIllDescription());
            existingPatient.getPatientIll().setPatientRegistration(updatedPatient.getPatientIll().getPatientRegistration());
            patientService.save(existingPatient); // Збережіть зміни

            System.out.println("Updated patient: " + existingPatient);
            return new ResponseEntity<>(existingPatient, HttpStatus.OK);
        } else {
            System.out.println("Patient not found for ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        System.out.println("Received request for patient ID: " + id);
        Optional<Patient> patient = Optional.ofNullable(patientService.findById(id));
        if (patient.isPresent()) {
            System.out.println("Found patient: " + patient.get());
            return ResponseEntity.ok(patient.get());
        } else {
            System.out.println("Patient not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    // Фільтрація пацієнтів
    @GetMapping("/filter")
    public String filterPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String illName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate regDate,
            Model model) {

        System.out.println("Filtering with parameters: name=" + name + ", illName=" + illName + ", dob=" + dob + ", regDate=" + regDate);

        // Фільтрація пацієнтів через сервіс
        List<Patient> patients = patientService.filterPatients(name, illName, dob, regDate);

        // Сортируем пациентов по имени (по алфавиту)
        patients.sort(Comparator.comparing(patient -> patient.getPatientName().toLowerCase()));

        model.addAttribute("patients", patients);

        // Лог для відстеження
        System.out.println("Filtered and sorted patients with name: " + name + ", illness: " + illName + ", dob: " + dob + ", regDate: " + regDate);

        // Повертаємо частину з таблицею пацієнтів
        return "mainTable"; // HTML-сторінка або фрагмент таблиці
    }



    @PostMapping("/create")
        public String createPatient(
                @RequestParam("name") String name,
                @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                @RequestParam("illName") String illName,
                @RequestParam("illDescription") String illDescription,
                @RequestParam("regDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate regDate,
                Model model) {
            System.out.println("======== Making a patient ===========");
            // Створюємо новий об'єкт хвороби
            Illnes newIllness = new Illnes();
            newIllness.setIllName(illName);
            newIllness.setIllDescription(illDescription);
            newIllness.setPatientRegistration(regDate);
            illnesService.saveIllnes(newIllness); // Зберігаємо хворобу

            // Створюємо нового пацієнта
            Patient newPatient = new Patient();
            newPatient.setPatientName(name);
            newPatient.setDateOfBirth(dob);
            newPatient.setPatientIll(newIllness); // Зв'язуємо пацієнта з хворобою
            patientService.save(newPatient); // Зберігаємо пацієнта
            System.out.println("======== Sucsesfully done ===========");

            // Додаємо пацієнта в модель для відображення
            model.addAttribute("patient", newPatient);

            // Повертаємо сторінку після створення
            return "redirect:/patients"; // Редірект на сторінку зі списком пацієнтів

        }


}
