package KeyboardInput;

import java.util.Scanner;

public class RectanglePropsCalculation {

    public static void main(String[] args) {
        try (Scanner con = new Scanner(System.in)) {
            while (con.hasNextInt()) {
                int a;
                int b;

                a = con.nextInt();
                b = con.nextInt();

                int perimeter = 2 * (a + b);
                int square = a * b;

                System.out.println(perimeter + " " + square);
            }
        }
    }
}
