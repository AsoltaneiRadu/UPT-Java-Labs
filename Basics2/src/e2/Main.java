package e2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
      List<Integer> lista = new ArrayList<>();
      try(Scanner scanner=new Scanner(new File("C:\\Users\\Radu\\Desktop\\TemeJava\\t1e2\\src\\e2\\in.txt"))){
          while(scanner.hasNextInt()){
              lista.add(scanner.nextInt());
          }
      }
      catch(FileNotFoundException e)
      {
          System.out.println("File not found");
          return;
      }
      if(lista.isEmpty())
      {
          System.out.println("Lista este goala");
          return;
      }
      int suma=Calcul.suma(lista);
      double media=Calcul.media(lista);
      int minim= Collections.min(lista);
      int maxim= Collections.max(lista);
      try(PrintWriter pw=new PrintWriter(new File("C:\\Users\\Radu\\Desktop\\TemeJava\\t1e2\\src\\e2\\out.txt"))){
          pw.println(suma+"\n"+media+"\n"+minim+"\n"+maxim);
      }
      catch(IOException e)
      {
          System.out.println("Problema la scrierea in fisier");
      }
    }
}