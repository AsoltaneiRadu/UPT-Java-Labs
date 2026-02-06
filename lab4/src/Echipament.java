import java.io.Serializable;
public class Echipament implements Serializable {
    private String denumire;
    private int nr_inv;
    private double pret;
    private String zona_mag;
    private StareEchipament stare;

    public Echipament(String denumire, int nr_inv, double pret, String zona_mag, StareEchipament stare) {
        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.stare = stare;
    }

    public int getNr_inv() {
        return nr_inv;
    }

    public StareEchipament getStare() {
        return stare;
    }

    public void setStare(StareEchipament stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "Denumire: " + denumire + ", Nr. Inv: " + nr_inv + ", Pret: " + pret +
                ", Zona: " + zona_mag + ", Stare: " + stare;
    }
}