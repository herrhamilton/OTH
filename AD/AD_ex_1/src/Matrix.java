import java.util.Scanner;

public class Matrix {
    public int[][] values;
    public int m;
    public int n;

    public Matrix(int m, int n) {
        if(m < 1 || n < 1){
            System.out.println("matrix dimensions must be > 0");
        }

        this.Init(m,n);
    }

    public void Init(int m, int n) {


        this.values = new int[m][n];
        this.m = m;
        this.n = n;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                this.values[i][j] = 0;
            }
        }
    }

    public void Print() {
        for(int m = 0; m < this.m; m++) {
            System.out.print("\n[ ");
            for (int n = 0; n < this.n; n++) {
                System.out.print(String.format("%3d", this.values[m][n]));
                System.out.print(n < this.n-1 ? "\t" : " ");
            }
            System.out.print("]");
        }
        System.out.println("\n");
    }

    public void Input() {
        Scanner s = new Scanner(System.in);

        for(int i=0; i<this.m; i++) {
            for(int j=0; j<this.n; j++) {
                System.out.println(String.format("Wert für Matrix[%d][%d]:", i, j));
                this.values[i][j] = s.nextInt();
            }
        }
        s.close();
        return;
    }

    public Matrix Add(Matrix M){
        if (M.m != this.m || M.n != this.n) {
            System.out.println("wrong matrix dimensions for addition");
            return null;
        }

        Matrix retVal = new Matrix(this.m, this.n);

        for(int m = 0; m < this.m; m++) {
            for (int n = 0; n < this.n; n++) {
                retVal.values[m][n] = this.values[m][n] + M.values[m][n];
            }
        }
        return retVal;

    }

    public Matrix Mul(Matrix M){
        if (this.n != M.m) {
            System.out.println("wrong matrix dimensions for multiplication");
            return null;
        }

        Matrix retMatrix = new Matrix(this.m, M.n);
        long mulCount = 0;
        long startTime = System.nanoTime();
        for(int i=0; i<retMatrix.m; i++) {
            for (int j=0; j<retMatrix.n; j++) {
                int temp = 0;
                for (int k=0; k<this.n; k++) {
                    temp += this.values[i][k] * M.values[k][j];
                    mulCount++;
                }
                retMatrix.values[i][j] = temp;
            }
        }
        long endTime = System.nanoTime();
        long elapsed = (endTime - startTime) / 1000000;

        long sizeFor1Min = mulCount/elapsed * 1000 * 60;
        System.out.println(sizeFor1Min);
        double mulFor1Min = Math.abs(Math.cbrt(sizeFor1Min));
        System.out.println("In 1min could be calculated matrices with dimensions of appr. " + mulFor1Min);

        long sizeFor5Min = mulCount/elapsed * 1000 * 60 * 5;
        System.out.println(sizeFor5Min);
        double mulFor5Min = Math.abs(Math.cbrt(sizeFor5Min));
        System.out.println("In 5min could be calculated matrices with dimensions of appr. " + mulFor5Min);

        System.out.println(String.format("(%dx%d) * (%dx%d) took %d multiplications and %d ms", this.m, this.n, M.m, M.n, mulCount, elapsed));

        return retMatrix;
    }
}
