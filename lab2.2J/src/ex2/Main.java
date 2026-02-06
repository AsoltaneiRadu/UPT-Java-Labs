package ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String []v=new String[41];
        int i=0;
        try(Scanner scanner=new Scanner(new File("C:\\Users\\Radu\\Desktop\\TemeJava\\lab2.2J\\src\\ex2\\cantec_in.txt"))){
            while(scanner.hasNextLine()){
                StringTokenizer st=new StringTokenizer(v[0],"*");
                while(st.hasMoreTokens()){
                    v[i]=st.nextToken();
                    i++;
                }
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

    }
}