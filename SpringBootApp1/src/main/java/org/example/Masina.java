package org.example;

public class Masina extends Autoturism
{
    private String model;
    private double pret;
    public Masina(String model, double pret, String firma, Combustibil combustibil)
    {
        super(firma, combustibil);
        this.model = model;
        this.pret = pret;
    }
    public String getModel()
    {
        return model;
    }
    public double getPret()
    {
        return pret;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public void setPret(double pret)
    {
        this.pret = pret;
    }
    @Override
    public String toString()
    {
        return "Masina{" +
                "model='" + model + '\'' +
                ", pret=" + pret +
                ", firma='" + getFirma() + '\'' +
                ", combustibil=" + getCombustibil() +
                '}';
    }
}
