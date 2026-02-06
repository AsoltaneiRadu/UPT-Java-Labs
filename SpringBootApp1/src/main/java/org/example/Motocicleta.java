package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Motocicleta extends Autoturism {
    private LocalDate anFabricatie;

    public Motocicleta(String firma, Combustibil combustibil, LocalDate anFabricatie) {
        super(firma, combustibil);
        this.anFabricatie = anFabricatie;
    }

    public LocalDate getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(LocalDate anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public int getVarsta() {
        if (this.anFabricatie == null) {
            return 0;
        }
        return Period.between(this.anFabricatie, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Motocicleta{" +
                "firma='" + getFirma() + '\'' +
                ", combustibil=" + getCombustibil() +
                ", anFabricatie=" + anFabricatie +
                ", varsta=" + getVarsta() + " ani" +
                '}';
    }
}
