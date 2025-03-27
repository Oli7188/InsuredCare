package com.example.insuredcare.controller;

import com.example.insuredcare.Poisteny;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/poisteny")  // Tento kontrolér správne používa /api/poisteny
@CrossOrigin(origins = "http://localhost:8080") // Povolenie požiadaviek z frontendu
public class PoistenyController {

    private final List<Poisteny> poistenyList = new ArrayList<>();

    public PoistenyController() {
        // Testovacie dáta
        poistenyList.add(new Poisteny(1L, "Jozef", "Novák", "Bratislava"));
        poistenyList.add(new Poisteny(2L, "Mária", "Kováčová", "Košice"));
    }

    @GetMapping
    public ResponseEntity<List<Poisteny>> getPoisteny() {
        return ResponseEntity.ok(poistenyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poisteny> getPoistenyById(@PathVariable Long id) {
        return poistenyList.stream()
                .filter(poisteny -> poisteny.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Poisteny> createPoisteny(@RequestBody Poisteny poisteny) {
        poisteny.setId((long) (poistenyList.size() + 1)); // Simulácia ID
        poistenyList.add(poisteny);
        return ResponseEntity.ok(poisteny);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poisteny> updatePoisteny(@PathVariable Long id, @RequestBody Poisteny updatedPoisteny) {
        for (Poisteny poisteny : poistenyList) {
            if (poisteny.getId().equals(id)) {
                poisteny.setMeno(updatedPoisteny.getMeno());
                poisteny.setPriezvisko(updatedPoisteny.getPriezvisko());
                poisteny.setAdresa(updatedPoisteny.getAdresa());
                return ResponseEntity.ok(poisteny);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoisteny(@PathVariable Long id) {
        boolean removed = poistenyList.removeIf(poisteny -> poisteny.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
