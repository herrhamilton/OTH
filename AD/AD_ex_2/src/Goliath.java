import java.util.Stack;

public class Goliath {

    static int n,m,rekCount,itCount = 0;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String args[]) {
        n=4;
        m=4;
        System.out.println("rek(" + n + "," + m + "): " + goliath_rek(n,m));
        System.out.println("count: " + rekCount);
        System.out.println("it(" + n + "," + m + "): " + goliath_it(n,m));
        System.out.println("count: " + itCount);
    }

    static int goliath_rek(int n, int m) {
        rekCount++;
        if(n == 0) {
            return m+1;
        }
        else if(m == 0) {
            return goliath_rek(n-1, 1);
        }
        else {
            return goliath_rek(n-1, goliath_rek(n, m-1));
        }
    }

    static int goliath_it(int n, int m) {
        while(true) {
            itCount++;
            if(n==0) {
                if(stack.empty()) {
                    return m + 1;
                }
                else {
                    n = stack.pop();
                    m = m + 1;
                }
            } else if(m==0) {
                n = n-1;
                m = 1;
            } else {
                stack.push(n-1);
                m = m-1;
            }
        }
    }
}
