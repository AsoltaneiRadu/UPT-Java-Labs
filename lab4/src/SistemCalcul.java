public class SistemCalcul extends Echipament {
    private String tip_mon;
    private double vit_proc;
    private int c_hdd;
    private SistemOperare sistem_operare;

    public SistemCalcul(String denumire, int nr_inv, double pret, String zona_mag, StareEchipament stare,
                        String tip_mon, double vit_proc, int c_hdd, SistemOperare sistem_operare) {
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.sistem_operare = sistem_operare;
    }

    public void setSistem_operare(SistemOperare sistem_operare) {
        this.sistem_operare = sistem_operare;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tip: Sistem Calcul, Monitor: " + tip_mon +
                ", CPU: " + vit_proc + "GHz, HDD: " + c_hdd + "GB, OS: " + sistem_operare;
    }
}