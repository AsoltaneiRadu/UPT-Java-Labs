import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String []v=new String[41];
        int i=0;
        try(Scanner scanner=new Scanner(new File("C:\\Users\\Radu\\Desktop\\TemeJava\\lab2java\\src\\judete_in.txt"))){
            while(scanner.hasNextLine()){
                v[i]=scanner.nextLine();
                i++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            return;
        }
        if(v.length==0){
            System.out.println("Lista este goala");
            return;
        }
        Aranjare.Sortare(v);
        Aranjare.CautareBinara(v);
    }
}