
    public class Imprimanta extends Echipament {
        private int ppm;
        private String rezolutie;
        private int p_car;
        private ModTiparire mod_tiparire;

        public Imprimanta(String denumire, int nr_inv, double pret, String zona_mag, StareEchipament stare,
                          int ppm, String rezolutie, int p_car, ModTiparire mod_tiparire) {
            super(denumire, nr_inv, pret, zona_mag, stare);
            this.ppm = ppm;
            this.rezolutie = rezolutie;
            this.p_car = p_car;
            this.mod_tiparire = mod_tiparire;
        }

        public void setMod_tiparire(ModTiparire mod_tiparire) {
            this.mod_tiparire = mod_tiparire;
        }

        @Override
        public String toString() {
            return super.toString() + ", Tip: Imprimanta, PPM: " + ppm + ", DPI: " + rezolutie +
                    ", Pag/Cartus: " + p_car + ", Mod: " + mod_tiparire;
        }
    }
