package badcode;

import badcode.Bill;
import badcode.SalesMan;
import badcode.Terminal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Create salesman");
        System.out.println("2 - Create salesman");




        Terminal terminal = Terminal.getInstance();
        SalesMan salesMan1 = new SalesMan("Anton");
        terminal.login(salesMan1);
        Bill bill = terminal.createBill(salesMan1);















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
