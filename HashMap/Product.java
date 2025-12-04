
class Product {

    private String productCode;
    private String name;
    private int quantity;

    public Product(String productCode, String name, int quantity) {
        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Некоректна кількість товару");
        }
    }

    @Override
    public String toString() {
        return "Код: " + productCode + ", Назва: " + name + ", Кількість: " + quantity + " шт.";
    }
}
