public class CMMDC {
    public int a;
    public int b;
    public CMMDC(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public static int cmmdc(int a, int b) {
        if(b==0)
            return Math.abs(a);
        return cmmdc(b,a%b);
        }
}
