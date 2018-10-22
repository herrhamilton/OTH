import java.util.ArrayList;
import java.util.List;

public class MaxTeilSum2D {
    Integer[][] matrix;
    int n;
    public static void main(String[] args) {
        MaxTeilSum2D sum = new MaxTeilSum2D(
                new Integer[][]{
                        { 3,  -3, -2},
                        {-1, -6,  2},
                        { 2, 6,  7}}, 3);

        System.out.println(sum.MaxTeilSum2D());
    }

    public MaxTeilSum2D(Integer[][] matrix, int n) {
        this.matrix = matrix;
        this.n = n;
    }

    public int MaxTeilSum2D(){
        int maxSum = -1000;
        int currentSum;
        List<Integer[]> tempLines = new ArrayList<>();

        for(int i=0; i<n; i++) {
            tempLines.add(i, matrix[i]);
            currentSum = MaxTeilSum1D(tempLines.get(i));
            maxSum = currentSum > maxSum ? currentSum : maxSum;
            for(int j=i+1; j<n; j++) {
                tempLines.add(i, Sum(tempLines.get(i),matrix[j]));
                currentSum = MaxTeilSum1D(tempLines.get(i));
                maxSum = currentSum > maxSum ? currentSum : maxSum;
            }
        }
        return maxSum;
}

    private Integer MaxTeilSum1D(Integer[] a) {
        int i, s, max = -1000, aktSum = 0;
        for (i=0; i<n; i++) {
            s = aktSum + a[i];
            if (s > a[i]) aktSum = s;
            else aktSum = a[i];
            if (aktSum > max) max = aktSum;
        }
        return max;
    }

    private Integer[] Sum(Integer[] a, Integer[] b) {
        Integer[] newArr = new Integer[n];
        for(int i=0; i<n; i++) {
            newArr[i] = a[i] + b[i];
        }
        return newArr;
    }
}
