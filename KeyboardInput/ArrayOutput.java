package KeyboardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayOutput {

    public static void main(String[] args) {

        int arrayLength;
        ArrayList<Integer> inputElements;

        try (Scanner con = new Scanner(System.in)) {
            arrayLength = con.nextInt();
            inputElements = new ArrayList<>(arrayLength);

            int enteredNumber;

            for (int i = 0; i < arrayLength; i++) {
                enteredNumber = con.nextInt();
                inputElements.add(enteredNumber);
            }
        }

        Collections.reverse(inputElements);

        String result = inputElements.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(result);
    }    
}
