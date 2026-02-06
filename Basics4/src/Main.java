import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int a=rand.nextInt(31);
        int b=rand.nextInt(31);
        System.out.println(a);
        System.out.println(b);
        System.out.println(CMMDC.cmmdc(a,b));
    }
}