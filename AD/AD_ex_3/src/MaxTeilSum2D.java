public class MaxTeilSum2D {
    public static void main(String args[]) {
        int matrix[][] = {
                  {   1,  -3,   5},
                  {   1,  -1,   2},
                  { -10,   5,   0}};
        System.out.println(maxTeilSum(matrix));
        return;
    }

    private static int maxTeilSum(int[][] matrix) {
        int i,j,startI, startJ = 0;
        int n = 3;
        int currentSum = 0;
        int maxTeilSum = -1000000;
        int tempSum = 0;

        for(startI=0; startI<n; startI++) {
            for(startJ=0; startJ<n; startJ++) {
                for(i=startI; i<n; i++) {
                    for (j = startJ; j < n; j++) {
                        tempSum = getTeilSum(matrix, startI, startJ, i, j);
                        currentSum = tempSum > matrix[i][j] ? tempSum : matrix[i][j];
                        if (currentSum > maxTeilSum) maxTeilSum = currentSum;
                    }
                }
            }
        }
        return maxTeilSum;
    }

    private static int getTeilSum(int[][] matrix, int startI, int startJ, int endI, int endJ) {
        int teilSum = 0;
        for(int i=startI; i<=endI; i++) {
            for(int j=startJ; j<=endJ; j++) {
                teilSum += matrix[i][j];
            }
        }
        return teilSum;
    }
}
