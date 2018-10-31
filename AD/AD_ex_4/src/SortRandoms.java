import java.util.Arrays;

public class SortRandoms {
    int n;
    int[] arr;

    public SortRandoms(int n) {
        this.n = n;
        this.arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = (int) (Math.random() * n);
        }
    }

    public static void main(String args[]) {
        SortRandoms mathias = new SortRandoms(170000);
        System.out.println("a: " + mathias.arr[0] +  "| b: " + mathias.arr[1] + "| c: " + mathias.arr[2]);
        System.out.println("ms: " + mathias.BubbleSort() / 1000000);
        System.out.println("a: " + mathias.arr[0] +  "| b: " + mathias.arr[1] + "| c: " + mathias.arr[2]);
        // (quick)sort: ca 555.555.555, bubbleSorT: ca 170.000
    }

    private double sort() {
        double startT = System.nanoTime();
        Arrays.sort(this.arr);
        double endT = System.nanoTime();
        return (endT - startT);
    }

    private double BubbleSort() {
        double startT = System.nanoTime();

        int temp;
        for(int i=n-2; i>0; i--) {
            for(int j=n-1; j>0; j--) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        double endT = System.nanoTime();
        return (endT - startT);
    }
}
