public class TwistedSimpleSorting {
    int[] arr;
    int n;

    public static void main(String[] args) {
        TwistedSimpleSorting bubbleArr = new TwistedSimpleSorting(-5, 13, -32, 7, -3, 17, 23, 12, -35, 19);
        TwistedSimpleSorting InsertionArr = new TwistedSimpleSorting(-5, 13, -32, 7, -3, 17, 23, 12, -35, 19);
        TwistedSimpleSorting SelectionArr = new TwistedSimpleSorting(-5, 13, -32, 7, -3, 17, 23, 12, -35, 19);

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


    public TwistedSimpleSorting(int... param) {
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
        for(int i=n-2; i>0; i--) {
            for(int j=n-1; j>0; j--) {
                if (arr[j] < arr[j-1]) {
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    public void InsertionSort() {
        int val;
        for(int i=n-1; i>0; i--) {
            if(arr[i-1] < arr[i]) {
                val = arr[i-1];
                int j=i;
                while(arr[j] < val && j<=n-1) {
                    arr[j-1] = arr[j];
                    j++;
                }
                if(j>n-1) j=n-1;
                arr[j] = val;
            }
        }
    }

    public void SelectionSort() {
        int maxIndex;
        int temp;
        for(int i=n-1; i>0; i--) {
            maxIndex = i;
            for(int j=i; j>0; j--) {
                maxIndex = arr[j] > arr[maxIndex] ? j : maxIndex;
            }
            temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }

    }
}
