package org.example;

public class Carte extends Produs {
    private String autor;
    private int anAparitie;
    public Carte(String nume , double pret, String autor, int anAparitie)
    {
        super(nume, pret);
        this.autor = autor;
        this.anAparitie = anAparitie;
    }

    public String getAutor() {
        return autor;
    }
    public int getAnAparitie() {
        return anAparitie;
    }
    @Override
    public String toString() {
        return "Carte{" +getNume()+
                "autor='" + autor + '\'' +
                ", anAparitie=" + anAparitie +"pret="+getPret()+
                '}';
    }
}
