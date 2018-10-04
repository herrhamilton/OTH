public class Exercise_1 {

    public static void main(String args[]) {
        for(int i = 30; i <= 40; i++) {
            for(int j = 30; j <= 40; j++) {
                System.out.println("---------------------");
                System.out.println(">> a: " + i + ", b: " +  j);
                System.out.println("ggT: " +  ggT(i, j));
                System.out.println("kgV: " +  kgV(i, j));
                System.out.println("a*b: " +  i*j);
                System.out.println("---------------------");
            }
        }
        return;
    }

    private static int ggT(int a, int b) {
        int r = -1;

        do {
            r = a%b;
            a = b;
            b = r;
        } while (r != 0);

        return a;
    }

    private  static int ggTRek(int a, int b) {
        if( b == 0) {
            return a;
        }

        int r = a%b;
        return ggTRek(b, r);
    }

    private  static int kgV(int a, int b) {
        int oldA = a;
        while (a%b != 0) {
            a += oldA;
        }
        return a;
    }
}
