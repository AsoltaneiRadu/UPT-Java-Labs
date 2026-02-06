package e2;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

import static java.sql.Types.NULL;

public class Calcul {
  public static int suma(List<Integer> lista){
      int s = 0;
      for(Integer i : lista){
          s += i;
      }
      return s;
  }
  public static double media(List<Integer> lista){
      if(lista.isEmpty())
          return 0;
      else
          return (double)suma(lista)/lista.size();
  }

}
