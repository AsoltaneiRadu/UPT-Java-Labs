package ex2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Vers {
    private String []v=new String[30];
    public Vers(){
        v=new String[30];
    }
   public static void Voc(String []v)
   {
       try(PrintWriter pw=new PrintWriter(new File("C:\\Users\\Radu\\Desktop\\TemeJava\\lab2.2J\\src\\ex2\\cantec_out.txt")){
           pw.println(v);
       }
       catch(IOException e)
           {
               System.out.println("Eroare la scrierea in fisier");       }
   }
}
