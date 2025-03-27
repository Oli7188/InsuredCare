package com.example.insuredcare.repository;

import com.example.insuredcare.Poisteny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoistenyRepository extends JpaRepository<Poisteny, Long> {
    // Ak potrebuješ ďalšie vlastné metódy na vyhľadávanie, môžeš ich pridať sem
}
