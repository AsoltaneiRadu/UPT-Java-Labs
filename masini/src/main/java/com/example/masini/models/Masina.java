package com.example.masini.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Masina {
    @Id
    private String nr_inmatriculare;
    private String marca;
    private int anul;
    private String culaore;
    private int km;
    public Masina(){}

    public Masina(String nr_inmatriculare, String marca, int anul, String culaore, int km) {
        this.nr_inmatriculare = nr_inmatriculare;
        this.marca = marca;
        this.anul = anul;
        this.culaore = culaore;
        this.km = km;
    }

    public String getNr_inmatriculare() {
        return nr_inmatriculare;
    }

    public void setNr_inmatriculare(String nr_inmatriculare) {
        this.nr_inmatriculare = nr_inmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnul() {
        return anul;
    }

    public void setAnul(int anul) {
        this.anul = anul;
    }

    public String getCulaore() {
        return culaore;
    }

    public void setCulaore(String culaore) {
        this.culaore = culaore;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

}
