
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayListLab {

    @SuppressWarnings("unchecked")
    // Тому що ми за умовою маємо юзати clone()
    public static void main(String[] args) {

        ArrayList<String> colors = new ArrayList<>(List.of(
                "Red",
                "Green",
                "Blue",
                "Yellow",
                "Black"
        ));

        colors.add("Orange");
        colors.add("Gray");


        System.out.println("1. Початковий список: " + colors);

        System.out.println("2. Ітерація по списку:");
        for (String color : colors) {
            System.out.println(" - " + color);
        }

        String white = "White";
        colors.add(0, white);
        System.out.println("3. Після вставки \"" + white + "\" на першу позицію: " + colors);

        int index = 2;
        String elementAtIndex = colors.get(index);
        System.out.println("4. Елемент за індексом " + index + ": " + elementAtIndex);

        colors.set(3, "Purple");
        System.out.println("5. Після оновлення елемента: " + colors);

        colors.remove(2);
        System.out.println("6. Після видалення третього елемента: " + colors);

        String searchColor = "Purple";
        int indexOfPurple = colors.indexOf(searchColor);
        System.out.println("7. Індекс елемента '" + searchColor + "' у масиві : " + indexOfPurple);

        Collections.sort(colors);
        System.out.println("8. Відсортований за алфавітом список: " + colors);

        ArrayList<String> copy = (ArrayList<String>) colors.clone();
        System.out.println("9. Копія списку: " + copy);
        Collections.reverse(copy);

        System.out.println("10. Реверс списку: " + copy);
        
        boolean areEqual = colors.equals(copy);
        System.out.println("11. Чи рівні оригінал і копія? " + areEqual);

        copy.clear();
        System.out.println("12. Копія після очищення: " + copy);

        System.out.println("13. Чи копія порожня? " + copy.isEmpty());

        colors.ensureCapacity(20);
        System.out.println("14. Збільшено місткість до 20: " + colors);

        colors.trimToSize();
        System.out.println("15. Після trimToSize(): " + colors);
    }
}
