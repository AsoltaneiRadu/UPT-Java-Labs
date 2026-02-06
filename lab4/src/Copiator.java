public class Copiator extends Echipament {
    private int p_ton;
    private FormatCopiere format_copiere;

    public Copiator(String denumire, int nr_inv, double pret, String zona_mag, StareEchipament stare,
                    int p_ton, FormatCopiere format_copiere) {
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.p_ton = p_ton;
        this.format_copiere = format_copiere;
    }

    public void setFormat_copiere(FormatCopiere format_copiere) {
        this.format_copiere = format_copiere;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tip: Copiator, Pag/Toner: " + p_ton + ", Format: " + format_copiere;
    }
}