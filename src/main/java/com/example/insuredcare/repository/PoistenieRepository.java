package com.example.insuredcare.repository;

import com.example.insuredcare.Poistenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoistenieRepository extends JpaRepository<Poistenie, Long> {

}



