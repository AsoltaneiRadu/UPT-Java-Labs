import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       Scanner scanner =new Scanner(System.in);
       int n;
       n=scanner.nextInt();
       int contor=0;
       System.out.println("Divizorii lui n sunt:");
       System.out.println(n);
        for (int i = n/2; i >=1; i--) {
            if(n%i==0){
                System.out.println(i);
                contor++;
            }
        }
        if(contor<=2){
            System.out.println("n este numar prim");
        }
    }
}