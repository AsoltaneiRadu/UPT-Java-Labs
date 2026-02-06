package org.example;

public class Chitara extends InstrumentMuzical{
    private TipChitara tip_chitara;
    private int nr_corzi;
    public Chitara(String producator, double pret, TipChitara tip_chitara, int nr_corzi)
    {
        super(producator, pret);
        this.tip_chitara = tip_chitara;
        this.nr_corzi = nr_corzi;
    }
    public TipChitara getTip_chitara() {
        return tip_chitara;
    }
    public void setTip_chitara(TipChitara tip_chitara) {
        this.tip_chitara = tip_chitara;
    }
    public int getNr_corzi() {
        return nr_corzi;

    }
    @Override
    public String toString() {
        return "Chitara{" +getProducator()+"pret="+getPret()+
                "tip_chitara=" + tip_chitara +
                ", nr_corzi=" + nr_corzi +
                '}';
    }
}
