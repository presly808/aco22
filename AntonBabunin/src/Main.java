import artcode.Bill;
import artcode.Product;

public class Main {
    public static void main(String[] args) {

        Product notebook = new Product("Notebook", 10000.99,1);
        Product smartfone = new Product("Smartphone", 5000.99,2);

        Bill firstBill = new Bill();
        firstBill.setProducts();
        firstBill.addProduct(notebook);
        firstBill.addProduct(smartfone);

        firstBill.delFromBill(smartfone);
        firstBill.delFromBill(smartfone);
        firstBill.closeBill();



        firstBill.addProduct(notebook);

        System.out.printf("Summ %.2f", firstBill.getAmountPrice());

    }
}
