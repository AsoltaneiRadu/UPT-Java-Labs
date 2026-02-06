package ex1;

public class Calcul
{
    public double lungime;
    public double latime;
    public Calcul(double lungime, double latime) {
        this.lungime = lungime;
        this.latime = latime;
    }
    public double CalcAria() {
        return lungime * latime;
    }
    public double CalcPerimetru(){
        return 2*(lungime + latime);
    }
}
