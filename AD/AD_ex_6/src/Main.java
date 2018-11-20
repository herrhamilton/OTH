import java.io.*;
import java.time.LocalTime;

public class Main {

    public static void main(String args[]) throws IOException {
// (CIRCULAR) LINKED LIST
/*

SORT DOES NOT WORK! BUT I ALREADY SPENT WAY TOO MUCH TIME FOR THIS, SHOULD HAVE USED C++ INSTEAD

    CircularList list = new CircularList();
        list.append(3);
        list.append(8);
        list.append(-2);
        list.append(12);
        list.print();

        list.sort();
        list.print();

*/
// LOTTO
/*
       Lotto lotto = new Lotto(49,6);
       lotto.getLottoZahlen();
       for(int winner : lotto.gewinner) {
           System.out.print(winner + ", ");
       }
*/

// BINARY SEARCH TREE
/*
DELETE DOES NOT WORK! BUT I ALREADY SPENT WAY TOO MUCH TIME FOR THIS, SHOULD HAVE USED C++ INSTEAD

       BinarySearchTree tree = new BinarySearchTree();
       tree.insert(5);
       tree.insert(3);
       tree.insert(1);
       tree.print();
       tree.delete(3);
       tree.print();
/*

// MATRIX
// Laufzeit/ Multiplikationen: Weniger Multiplikationen, aber auch langsamer. Getestet bis m=4096.
// Trend: Wird mit größeren Dimensionen schneller

        FileWriter out = new FileWriter("C:\\Users\\jouho\\Desktop\\Log\\Log.txt");

        long timeA;
        long timeB;
        long timeC;
        long durationGood;
        long durationBad;
        int i=1;
        int size = 2;
        do {
            size = (int) Math.pow(2, i);
            System.out.println("Now testing 2^" + i + " = " + size + "...\n" + LocalTime.now());
            out.write("\nNow testing 2^" + i + " = " + size + "...\n" + LocalTime.now() + "\n" );

            Matrix M = new Matrix(size);
            M.Init();
            Matrix N = new Matrix(size);
            N.Init();
            timeA = System.nanoTime();
            Matrix O = M.BetterMul(M, N);
            timeB = System.nanoTime();
            Matrix P = M.Mul(N);
            timeC = System.nanoTime();
            durationGood = timeB - timeA;
            durationBad = timeC - timeB;

            System.out.println("Good: " + durationGood + "\n" + "Bad:  " + durationBad + "\n");
            out.write("Good: " + durationGood + "\n" + "Bad:  " + durationBad + "\n");
            i++;
        } while(durationBad < durationGood && i < 32);

        if(i < 31) {
            System.out.println("With a Matrix size of 2^" + i + ", the good algorithm is better. Wow..");
            out.write("With a Matrix size of 2^" + i + ", the good algorithm is better. Wow..");
        }
        else {
            System.out.println("Looks like the 'good' algorithm is never better... Whoa.");
            out.write("Looks like the 'good' algorithm is never better... Whoa.");
        }
        out.close();

        //M.Print();
        //N.Print();
        //O.Print();
        //P.Print();
*/
    }
}
