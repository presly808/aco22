package week1.model;

public class Bill {

    private static final int MAX_COUNT_OF_PRODUCTS_IN_BILL = 10;

    private Product[] products = new Product[MAX_COUNT_OF_PRODUCTS_IN_BILL];
    private int productsCount;
    private int id;
    private double amountPrice;

    private boolean isClosed;

    private Salesman salesman;

    private CloseTime closeTime;

    public Bill(Salesman salesman, int idOfBill) {
        this.salesman = salesman;
        this.id = idOfBill;
    }

    public void addProduct(String name, int id, double price) {
        if (productsCount == MAX_COUNT_OF_PRODUCTS_IN_BILL) {
            System.out.println("sorry, max count of products in bill");

        } else if (name == null || id == 0 || price == 0.0) {
            System.out.println("wrong data");

        } else if (isClosed) {
            System.out.println("sorry, bill is closed");

        } else {
            products[productsCount++] = new Product(name, id, price);
            productsCount++;
            calculateAmountPrice();
        }
    }

    private void calculateAmountPrice() {
        for (int i = 0; i < productsCount; i++) {
            amountPrice += products[i].getPrice();
        }
    }

    public void printBill() {
        for (int i = 0; i < productsCount; i++) {
            System.out.println(products[i].getName() + ": " + products[i].getPrice());
        }

        System.out.printf("id: %d, amount price: %.2f, saleman: %s",
                id, amountPrice, salesman.getFullName());

    }

    public void closeBill(int hours, int minutes, int seconds) {
        isClosed = true;
        closeTime = new CloseTime(hours, minutes, seconds);
        printBill();
        salesman.setSumOfAllSales(amountPrice);
    }

    public void setProducts(String name, int id, double price) {
        addProduct(name, id, price);
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public int getId() {
        return id;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public Product[] getProducts() {
        return products;
    }
}
