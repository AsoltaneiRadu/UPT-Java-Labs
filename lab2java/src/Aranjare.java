import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Aranjare {
public static void Sortare(String []v){
    Arrays.sort(v);
    for(String str : v)
        System.out.println(str);
    }

public static void CautareBinara(String []v){
    Scanner sc = new Scanner(System.in);
    String x=sc.nextLine();
    int poz= Arrays.binarySearch(v, x);
    System.out.println(poz);
    sc.close();
}
}
