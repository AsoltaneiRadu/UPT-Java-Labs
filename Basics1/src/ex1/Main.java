package ex1;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Lungime:");
        double lungime=scanner.nextDouble();
        System.out.println("Latime:");
        double latime=scanner.nextDouble();
        Calcul calcul=new Calcul(lungime,latime);
        System.out.println("Perimetru:"+calcul.CalcPerimetru());
        System.out.println("Aria:"+calcul.CalcAria());
        scanner.close();
    }
}