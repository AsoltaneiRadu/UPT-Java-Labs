package ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<produs> produse = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("produse.csv"))){
            String line;
            while((line=br.readLine())!=0)
            {
                String[] desp=line.split(",");
                if(desp.length==4)
                {
                    String nume=desp[0].trim();
                    double pret=Double.parseDouble(desp[1].trim());
                    int cantitate=Integer.parseInt(desp[2].trim());
                    LocalDateTime date=LocalDateTime.parse(desp[3].trim());

                    Produs p=new Produs()
                }
            }
        }
        int optiune;
        do{
            System.out.println("1.Afisarea produselor");
            System.out.println("2.Afisarea produselor expirate");
            System.out.println("3.Cumpara produs");
            System.out.println("4.Afisarea produselor cu pret minim");
            System.out.println("5.Salvare produse");
            System.out.println("Alege optiunea:");
            optiune = sc.nextInt();
            switch(optiune){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
        while(optiune!=0);
        {
            sc.close();
        }
    }
}