package com.example.medicalwebapp.repository;

import com.example.medicalwebapp.model.Illnes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnesRepository extends JpaRepository<Illnes, Integer> {

    // Пошук захворювання за назвою
    Illnes findByIllName(String illName);

    // Перевірка наявності захворювання за назвою
    boolean existsByIllName(String illName);
}
