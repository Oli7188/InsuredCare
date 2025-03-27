package com.example.insuredcare;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Poisteny { // ✅ Opravený názov triedy (s veľkým P)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String meno;

    @Column(nullable = false)
    private String priezvisko;

    @Column(nullable = false)
    private String adresa;

    @OneToMany(mappedBy = "poisteny", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poistenie> poistenia;

    // Bezparametrický konštruktor
    public Poisteny() {}

    // ✅ Opravený konštruktor, ktorý zahŕňa aj ID
    public Poisteny(Long id, String meno, String priezvisko, String adresa) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.adresa = adresa;
    }

    // ✅ Konštruktor bez ID (napríklad pre POST request)
    public Poisteny(String meno, String priezvisko, String adresa) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.adresa = adresa;
    }

    // ✅ Gettery a settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Poistenie> getPoistenia() {
        return poistenia;
    }

    public void setPoistenia(List<Poistenie> poistenia) {
        this.poistenia = poistenia;
    }
}

