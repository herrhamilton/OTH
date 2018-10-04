import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise_2 {

    public static void main( String args[]) {
        int upperBound = 100000;
        String[] numbers = new String[upperBound];

        for (int i = 0; i < upperBound; i++) {
            numbers[i] = "" + (i+1);
        }
       for (int i = 1; i < upperBound; i++) {
            for (int j = i+1; j < upperBound; j++) {
                try {
                    if (Integer.parseInt(numbers[j]) % Integer.parseInt(numbers[i]) == 0) {
                        numbers[j] = "-";
                    }
                }
                catch(Exception e) {}
            }
       }

       for (int i = 1; i < upperBound; i++) {
           System.out.print((i % 10 == 0) ? (numbers[i] + " \n") : (numbers[i] + "\t"));
       }

        System.out.println("---------------------\n\n");

        int lineBreak = 0;

        for(String number : numbers) {
            if (!number.equals("-")) {
                System.out.print(number + ", ");
                lineBreak++;

            }

            if(lineBreak == 20) {
                lineBreak = 0;
                System.out.print("\n");
            }
        }
    }
}
