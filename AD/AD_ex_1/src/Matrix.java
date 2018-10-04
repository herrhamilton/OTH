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
            System.out.println("\n");
            for (int n = 0; n < this.n; n++) {
                System.out.print(this.values[m][n] + "\t");
            }
        }
    }

    public void Input() {
        // get Matrix from keyboard input
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
        retVal.Print();
        return retVal;

    }

    public Matrix Mul(Matrix M){
        if (M.m != this.n || M.n != this.m) {
            System.out.println("wrong matrix dimensions for multiplication");
            return null;
        }

        Matrix retVal = new Matrix(this.m, M.n);

        for(int i = 0; i < retVal.m; i++) {
            for(int j = 0; j < retVal.n; j++) {
                int temp = 0;
                for(int val = 0; val < this.n; val++) {
                    temp += this.values[i][val] * M.values[val][i];
                }
                retVal.values[i][j] = temp;
            }
        }

        retVal.Print();
        return retVal;
    }
}
