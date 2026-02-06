package org.example;

public class Joc extends Produs {
    public enum Console {

    }
    private  Console console;
    private  String gen;
    public Joc(String nume, double pret, Console console, String gen)
    {
        super(nume, pret);
        this.console = console;
        this.gen = gen;
    }

    public Console getConsole() {
        return console;
    }

    public String getGen() {
        return gen;
    }
    @Override
    public String toString() {
        return "Joc{" +getNume()+
                "pret="+getPret()+
                "console=" + console +
                ", gen='" + gen + '\'' +
                '}';
    }
}
