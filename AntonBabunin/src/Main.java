import artcode.shop.personal.Bill;
import artcode.shop.personal.SalesMan;
import artcode.shop.personal.Terminal;
import artcode.shop.product.Product;

public class Main {
    public static void main(String[] args) {

        Terminal terminal = Terminal.getInstance();
        SalesMan salesMan1 = new SalesMan("Anton");
        terminal.login(salesMan1);
        Bill bill = terminal.createBill();















        /*

        Product notebook = new Product("Notebook", 10000.99123,1);
        Product smartfone = new Product("Smartphone", 5000.99123,2);

        Bill firstBill = new Bill();
//        firstBill.setProducts();
        firstBill.addProduct(notebook);
        firstBill.addProduct(smartfone);

        firstBill.delFromBill(smartfone);
        firstBill.delFromBill(smartfone);
        firstBill.closeBill();



        firstBill.addProduct(notebook);

        System.out.printf("Summ %.2f", firstBill.getAmountPrice());
*/
    }
}
