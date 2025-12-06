package KeyboardInput;

import java.util.Scanner;

public class RectanglePropsCalculation {

    public static void main(String[] args) {
        try (Scanner con = new Scanner(System.in)) {
            int a;
            int b;
            while (con.hasNextInt()) {

                a = con.nextInt();
                b = con.nextInt();

                int perimeter = 2 * (a + b);
                int square = a * b;

                System.out.println(perimeter + " " + square);
            }
        }
    }
}
