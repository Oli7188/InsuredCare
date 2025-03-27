package com.example.insuredcare.controller;

import com.example.insuredcare.Poistenie;
import com.example.insuredcare.service.PoistenieService;
import com.example.insuredcare.service.PoistenyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/poistenie")  // Zmena cesty na /api/poistenie
public class PoistenieController {

    private final PoistenieService poistenieService;
    private final PoistenyService poistenyService;

    @Autowired
    public PoistenieController(PoistenieService poistenieService, PoistenyService poistenyService) {
        this.poistenieService = poistenieService;
        this.poistenyService = poistenyService;
    }

    @GetMapping
    public List<Poistenie> getPoistenia() {
        return poistenieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poistenie> getPoistenieById(@PathVariable Long id) {
        Optional<Poistenie> poistenie = poistenieService.findById(id);
        return poistenie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Poistenie createPoistenie(@RequestBody Poistenie poistenie) {
        return poistenieService.save(poistenie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poistenie> updatePoistenie(@PathVariable Long id, @RequestBody Poistenie updatedPoistenie) {
        Optional<Poistenie> existingPoistenie = poistenieService.findById(id);
        if (existingPoistenie.isPresent()) {
            Poistenie poistenie = existingPoistenie.get();
            poistenie.setTyp(updatedPoistenie.getTyp());
            poistenie.setSuma(updatedPoistenie.getSuma());
            return ResponseEntity.ok(poistenieService.save(poistenie));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoistenie(@PathVariable Long id) {
        Optional<Poistenie> poistenie = poistenieService.findById(id);
        if (poistenie.isPresent()) {
            poistenieService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
