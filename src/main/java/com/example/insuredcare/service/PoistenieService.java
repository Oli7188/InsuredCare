package com.example.insuredcare.service;

import com.example.insuredcare.Poistenie;
import com.example.insuredcare.repository.PoistenieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoistenieService {
    private final PoistenieRepository repository;

    public PoistenieService(PoistenieRepository repository) {
        this.repository = repository;
    }

    public List<Poistenie> findAll() {
        return repository.findAll();
    }

    public Optional<Poistenie> findById(Long id) {
        return repository.findById(id);
    }

    public Poistenie save(Poistenie poistenie) {
        return repository.save(poistenie);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

