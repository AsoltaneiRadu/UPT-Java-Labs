package org.example;

public abstract class Autoturism {
    private String firma;
    private Combustibil combustibil;
    public Autoturism(String firma, Combustibil combustibil) {
        this.firma = firma;
        this.combustibil = combustibil;

    }
    public String getFirma() {
        return firma;
    }
    public Combustibil getCombustibil() {
        return combustibil;
    }
    public void setCombustibil(Combustibil combustibil) {
        this.combustibil = combustibil;
        }
    public void setFirma(String firma) {
        this.firma = firma;
    }

}
