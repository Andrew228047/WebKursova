package com.example.medicalwebapp.service;

import com.example.medicalwebapp.model.Illnes;
import com.example.medicalwebapp.repository.IllnesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnesService {

    private final IllnesRepository illnesRepository;

    @Autowired
    public IllnesService(IllnesRepository illnesRepository) {
        this.illnesRepository = illnesRepository;
    }

    // Отримання всіх захворювань
    public List<Illnes> getAllIllneses() {
        return illnesRepository.findAll();
    }

    // Отримання захворювання за ID
    public Illnes getIllnesById(Integer id) {
        return illnesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Illnes not found with id " + id));
    }

    // Створення нового захворювання
    public Illnes saveIllnes(Illnes illnes) {
        return illnesRepository.save(illnes);
    }

    // Оновлення захворювання
    public Illnes updateIllnes(Integer id, Illnes illnesDetails) {
        Illnes existingIllnes = illnesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Illnes not found with id " + id));

        existingIllnes.setIllName(illnesDetails.getIllName()); // Оновлюємо назву хвороби
        return illnesRepository.save(existingIllnes);
    }

    // Видалення захворювання
    public void deleteIllnes(Integer id) {
        Illnes existingIllnes = illnesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Illnes not found with id " + id));
        illnesRepository.delete(existingIllnes);
    }


}
