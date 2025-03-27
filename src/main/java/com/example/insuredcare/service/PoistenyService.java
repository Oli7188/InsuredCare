package com.example.insuredcare.service;

import com.example.insuredcare.Poisteny;

import com.example.insuredcare.repository.PoistenyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoistenyService {
    private final PoistenyRepository repository;

    public PoistenyService(PoistenyRepository repository) {
        this.repository = repository;
    }

    public List<Poisteny> findAll() {
        return repository.findAll();
    }

    public Optional<Poisteny> findById(Long id) {
        return repository.findById(id);
    }

    public Poisteny save(Poisteny poisteny) {
        return repository.save(poisteny);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}


