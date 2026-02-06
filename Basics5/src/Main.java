import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(22);
        System.out.println(n);
        if(VerifFibbo.Fibbo(n)==true)
        {
            System.out.println("apartine sirului Fibbonacci");
        }
        else
            System.out.println("nu apartine sirului Fibbonacci");
    }
}