public class VerifFibbo {
    public int x;
    public VerifFibbo(int x)
    {
        this.x=x;
    }
    public static boolean pp(int x)
    {
        int s=(int)Math.sqrt(x);
        return s*s==x;
    }
    public static boolean Fibbo(int x)
    {
        return pp(5*x*x-4) || pp(5*x*x+4);
    }
}
