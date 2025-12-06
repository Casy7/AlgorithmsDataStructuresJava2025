package KeyboardInput;

import java.util.Scanner;

public class KeyboardInput {
    public static void main(String[] args) {
        try (Scanner con = new Scanner(System.in)) {
            int x = con.nextInt();
            int y;

            if (x < 5)
            {
                y = x*x - 3*x + 4;
            }
            else
            {
                y = x + 7;
            }

            System.out.println(y);
        }
    }
}
