public class SimpleSorting {
    int[] arr;
    int n;

    public static void main(String[] args) {
        SimpleSorting bubbleArr = new SimpleSorting(-5, 13, -32, 7, -3, 17, 23, 12, -35, 19);
        SimpleSorting InsertionArr = new SimpleSorting(-5, 13, -32, 7, -3, 17, 23, 12, -35, 19);
        SimpleSorting SelectionArr = new SimpleSorting(-5, 13, -32, 7, -3, 17, 23, 12, -35, 19);

        System.out.println("unsortiert:");
        bubbleArr.print();

        System.out.println("Bubble Sort:");
        bubbleArr.BubbleSort();
        bubbleArr.print();

        System.out.println("Insertion Sort:");
        InsertionArr.BubbleSort();
        InsertionArr.print();

        System.out.println("Selection Sort:");
        SelectionArr.BubbleSort();
        SelectionArr.print();


    }


    public SimpleSorting(int... param) {
        this.n = param.length;
        this.arr = new int[n];

        for(int i=0; i<param.length; i++)
            this.arr[i] = param[i];
    }

    public void print() {
        System.out.print("\n");
        for(int val : arr) {
            System.out.print("\t\t" + val);
        }
        System.out.print("\n");
    }

    public void BubbleSort() {
        int temp;
        for(int i=0; i<n-2; i++) {
            for(int j=0; j<n-1; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public void InsertionSort() {
        int val;
        for(int i=0; i<n-1; i++) {
            if(arr[i+1] < arr[i]) {
                val = arr[i+1];
                int j=i;
                while(arr[j] > val && j>=0) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                if(j<0) j=0;
                arr[j] = val;
            }
        }
    }

    public void SelectionSort() {
        int minIndex;
        int temp;
        for(int i=0; i<n-1; i++) {
            minIndex = i;
            for(int j=i; j<n; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

    }
}
