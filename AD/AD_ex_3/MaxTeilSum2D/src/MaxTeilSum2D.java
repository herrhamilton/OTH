public class MaxTeilSum2D {
    int[][] matrix;
    int n;
    public static void main(String[] args) {
        MaxTeilSum2D sum = new MaxTeilSum2D(
                new int[][]{
                { 3,  4, -5},
                {-1, -6,  2},
                { 2, -4,  7}}, 3);

        System.out.println(sum.MaxTeilSum2D());
    }

    public MaxTeilSum2D(int[][] matrix, int n) {
        this.matrix = matrix;
        this.n = n;
    }

    public int MaxTeilSum2D(){
        int maxSum = -1000;
        int currentSum = 0;
        int[] tempLine = matrix[0];

        // line for line with 1d
        // add multiple lines, then 1d
            currentSum = MaxTeilSum1D(matrix[i]);
            maxSum = currentSum > maxSum ? currentSum : maxSum;
        }

        return maxSum;
    }

    private int MaxTeilSum1D(int[] zeile) {

        return sum;
    }
}
