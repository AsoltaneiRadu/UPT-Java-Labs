package ex3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Introduceti textul:");
        String text=sc.nextLine();
        StringBuilder sb = new StringBuilder(text);
        System.out.println("Introduceti textul de inserat");
        String text2=sc.nextLine();
        System.out.println("Introduceti pozitia de inserat");
        int pozitia=sc.nextInt();
        sb.insert(pozitia,text2);
        System.out.println("Text dupa inserare:"+sb);
        System.out.println("Introduceti pozitia de stergere");
        int start=sc.nextInt();
        System.out.println("Introduceti nr de caract de sters");
        int stop=sc.nextInt();
        int end=Math.min(start+stop,sb.length());
        sb.delete(start,end);
        System.out.println("Text dupa stergere:"+sb);
        sc.close();
    }
}
