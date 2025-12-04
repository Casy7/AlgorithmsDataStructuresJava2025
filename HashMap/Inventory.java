import java.util.HashMap;
import java.util.Map;

class Inventory {
        private Map<String, Product> storage;

    public Inventory() {
                this.storage = new HashMap<>();
    }

        public void addProduct(Product product) {
        String code = product.getProductCode();
                if (storage.containsKey(code)) {
            System.out.println("Товар з кодом " + code + " ВЖЕ є на складі");
                                } else {
            storage.put(code, product);
            System.out.println("Товар '" + product.getName() + "' додано.");
        }
    }

        public void removeProduct(String productCode) {
        if (storage.containsKey(productCode)) {
            Product removed = storage.remove(productCode);
            System.out.println("Товар '" + removed.getName() + "' з кодом " + productCode + " ВИДАЛЕНО.");
        } else {
            System.out.println("Товар з кодом " + productCode + " не знайдено.");
        }
    }

            public Product findProduct(String productCode) {
                Product product = storage.get(productCode);
        if (product == null) {
            System.out.println("Товар з кодом " + productCode + " не знайдено.");
        }
        return product;
    }

        public void displayAllProducts() {
        if (storage.isEmpty()) {
            System.out.println("Немає товарів на складі");
            return;
        }
        
        System.out.println("\n---  ІНВЕНТАР МАГАЗИНУ (" + storage.size() + " од.) ---");
                for (Product product : storage.values()) {
            System.out.println(product);         }
        System.out.println("_________________________________");
    }
}