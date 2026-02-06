package ex1;

import java.time.LocalDate;

public class produs {
    private String nume;
    private double pret;
    private int cantitate;
    private LocalDate date;
    public produs(String nume, double pret, int cantitate, LocalDate date) {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
        this.date = date;
    }
    public String getNume() { return  nume; }
    public void setNume(String nume) { this.nume = nume; }
    public double getPret() { return pret; }
    public void setPret(double pret) { this.pret = pret; }
    public int getCantitate() { return cantitate; }
    public void setCantitate(int cantitate) { this.cantitate = cantitate; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    @Override
    public String toString() {
        return "Produs{"+nume+pret+cantitate+date+"}";

    }

}
