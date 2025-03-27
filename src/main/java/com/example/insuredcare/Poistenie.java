package com.example.insuredcare;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Poistenie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Typ poistenia je povinný")
    @Column(nullable = false)
    private String typ;

    @Positive(message = "Suma musí byť kladná")
    @Column(nullable = false)
    private double suma;

    @ManyToOne
    @JoinColumn(name = "poisteny_id", nullable = false)
    private Poisteny poisteny;

    // Bezparametrický konštruktor
    public Poistenie() {}

    public Poistenie(String typ, double suma, Poisteny poisteny) {
        this.typ = typ;
        this.suma = suma;
        this.poisteny = poisteny;
    }

    // Getter a setter pre ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter a setter pre Typ
    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    // Getter a setter pre Suma
    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    // Getter a setter pre Poisteny
    public Poisteny getPoisteny() {
        return poisteny;
    }

    public void setPoisteny(Poisteny poisteny) {
        this.poisteny = poisteny;
    }
}


