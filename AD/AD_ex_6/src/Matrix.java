import java.util.Random;
import java.util.Scanner;

public class Matrix {
    public int[][] values;
    public int m;
    public int n;

    public Matrix(int m) {
        if(m == 0 || m%2 != 0){
            throw new RuntimeException("Wrong Matrix size! Must be square and greater than 2");
        }
        this.values = new int[m][m];
        this.m = m;
        this.n = m;
    }

    public void Init() {
        Random rand = new Random();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                this.values[i][j] = rand.nextInt(10);
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

    public Matrix Add(Matrix M) {
        return this.Add(M, 1);
    }

    public Matrix Sub(Matrix M){
        return this.Add(M, -1);
    }

    public Matrix Add(Matrix M, int pre) {
        if (M.m != this.m || M.n != this.n) {
            System.out.println("wrong matrix dimensions for addition");
            return null;
        }

        Matrix retVal = new Matrix(this.m);

        for(int m = 0; m < this.m; m++) {
            for (int n = 0; n < this.n; n++) {
                retVal.values[m][n] = this.values[m][n] + pre * M.values[m][n];
            }
        }
        return retVal;
    }

    public Matrix Mul(Matrix M){
        if (this.n != M.m) {
            System.out.println("wrong matrix dimensions for multiplication");
            return null;
        }

        Matrix retMatrix = new Matrix(this.m);

        for(int i=0; i<retMatrix.m; i++) {
            for (int j=0; j<retMatrix.n; j++) {
                int temp = 0;
                for (int k=0; k<this.n; k++) {
                    temp += this.values[i][k] * M.values[k][j];
                }
                retMatrix.values[i][j] = temp;
            }
        }
        return retMatrix;
    }

    private Matrix M11(Matrix M) {
        Matrix ret = new Matrix((M.m/2));

        for(int i=0; i<ret.m; i++) {
            for(int j=0; j<ret.m; j++) {
                ret.values[i][j] = M.values[i][j];
            }
        }
        return ret;
    }

    private Matrix M12(Matrix M) {
        Matrix ret = new Matrix((M.m/2));

        for(int i=0; i<ret.m; i++) {
            for(int j=0; j<ret.m; j++) {
                ret.values[i][j] = M.values[i][j+ret.m];
            }
        }
        return ret;
    }

    private Matrix M21(Matrix M) {
        Matrix ret = new Matrix((M.m/2));

        for(int i=0; i<ret.m; i++) {
            for(int j=0; j<ret.m; j++) {
                ret.values[i][j] = M.values[i+ret.m][j];
            }
        }
        return ret;
    }

    private Matrix M22(Matrix M) {
        Matrix ret = new Matrix((M.m/2));

        for(int i=0; i<ret.m; i++) {
            for(int j=0; j<ret.m; j++) {
                ret.values[i][j] = M.values[i+ret.m][j+ret.m];
            }
        }
        return ret;
    }

    private Matrix Build(Matrix O11, Matrix O12, Matrix O21, Matrix O22) {
        Matrix ret = new Matrix(2*O11.m);

        for(int i=0; i<ret.m/2; i++) {
            for(int j=0; j<ret.m/2; j++) {
                ret.values[i][j] = O11.values[i][j];
            }
        }
        for(int i=0; i<ret.m/2; i++) {
            for(int j=0; j<ret.m/2; j++) {
                ret.values[i][j+ret.m/2] = O12.values[i][j];
            }
        }
        for(int i=0; i<ret.m/2; i++) {
            for(int j=0; j<ret.m/2; j++) {
                ret.values[i+ret.m/2][j] = O21.values[i][j];
            }
        }
        for(int i=0; i<ret.m/2; i++) {
            for(int j=0; j<ret.m/2; j++) {
                ret.values[i+ret.m/2][j+ret.m/2] = O22.values[i][j];
            }
        }
        return ret;
    }

    public Matrix BetterMul(Matrix M, Matrix N) {
        Matrix O = null;

        if (M.m > 2) {
            Matrix M11 = M11(M);
            Matrix M12 = M12(M);
            Matrix M21 = M21(M);
            Matrix M22 = M22(M);

            Matrix N11 = M11(N);
            Matrix N12 = M12(N);
            Matrix N21 = M21(N);
            Matrix N22 = M22(N);

            // calc(m/2);
            Matrix H1 = BetterMul(M11.Add(M22), N11.Add(N22));
            Matrix H2 = BetterMul(M21.Add(M22), N11);
            Matrix H3 = BetterMul(M11, N12.Sub(N22));
            Matrix H4 = BetterMul(M22, N21.Sub(N11));
            Matrix H5 = BetterMul(M11.Add(M12), N22);
            Matrix H6 = BetterMul(M21.Sub(M11), N11.Add(N12));
            Matrix H7 = BetterMul(M12.Sub(M22), N21.Add(N22));

            Matrix O11 = H1.Add(H4).Sub(H5).Add(H7);
            Matrix O12 = H3.Add(H5);
            Matrix O21 = H2.Add(H4);
            Matrix O22 = H1.Sub(H2).Add(H3).Add(H6);

            O = Build(O11, O12, O21, O22);

        }
        else {
            O = new Matrix(2);
            int M11 = M.values[0][0];
            int M12 = M.values[0][1];
            int M21 = M.values[1][0];
            int M22 = M.values[1][1];

            int N11 = N.values[0][0];
            int N12 = N.values[0][1];
            int N21 = N.values[1][0];
            int N22 = N.values[1][1];

            int H1 = (M11+M22) * (N11+N22);
            int H2 = (M21+M22) * N11;
            int H3 =      M11  * (N12-N22);
            int H4 =      M22  * (N21-N11);
            int H5 = (M11+M12) * N22;
            int H6 = (M21-M11) * (N11 + N12);
            int H7 = (M12-M22) * (N21+N22);

            O.values[0][0] = H1 + H4 - H5 + H7;
            O.values[0][1] = H3 + H5;
            O.values[1][0] = H2 + H4;
            O.values[1][1] = H1 - H2 + H3 + H6;
        }
        return O;
    }
}
