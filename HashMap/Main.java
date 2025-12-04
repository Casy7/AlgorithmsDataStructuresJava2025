// Варіант 4

public class Main {

    public static void main(String[] args) {

        Inventory myInventory = new Inventory();

        Product p1 = new Product("A123", "хліб Обідній", 10);
        Product p2 = new Product("B456", "молоко Бурьонка 1л", 5);
        Product p3 = new Product("C789", "банани Усмішка Буданова", 22);

        myInventory.addProduct(p1);
        myInventory.addProduct(p2);
        myInventory.addProduct(p3);

        myInventory.addProduct(new Product("A123", "хліб вечірній", 1));

        myInventory.displayAllProducts();

        Product foundItem = myInventory.findProduct("B456");

        if (foundItem != null) {
            System.out.println("Знайдено: " + foundItem.getName());
            foundItem.setQuantity(foundItem.getQuantity() - 3);
            System.out.println("Нова кількість молока: " + foundItem.getQuantity());
        }

        myInventory.findProduct("Z999");
        myInventory.removeProduct("A123");

        myInventory.displayAllProducts();
    }
}
