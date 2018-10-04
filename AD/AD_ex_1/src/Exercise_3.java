public class Exercise_3 {

    public static void main(String args[]) {
        Matrix mat = new Matrix(2,2);
        Matrix bob = new Matrix(2, 2);

        mat.values[0][0] = 1;
        mat.values[0][1] = 2;
        mat.values[1][0] = 3;
        mat.values[1][1] = 4;

        mat.Print();

        bob.values[0][0] = 2;
        bob.values[0][1] = 3;
        bob.values[1][0] = 2;
        bob.values[1][1] = 3;

        bob.Print();
        mat.Mul(bob);
    }

}
