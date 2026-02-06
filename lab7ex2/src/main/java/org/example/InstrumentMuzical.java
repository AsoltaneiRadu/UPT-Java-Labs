package org.example;

public  abstract class InstrumentMuzical {
    private String producator;
    private double pret;
    public InstrumentMuzical(String producator, double pret)
    {
        this.producator = producator;
        this.pret = pret;
    }
    public String getProducator() {
        return producator;
    }
    public void setProducator(String producator) {
        this.producator = producator;
    }
    public double getPret() {
        return pret;
    }
    public void setPret(double pret) {
        this.pret = pret;
    }
}
