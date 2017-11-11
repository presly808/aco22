package market;

import market.Bill;
import market.BillTime;
import market.Product;
import market.Salesman;

public class CashboxMain {
    public static void main(String[] args) {
        StartTest();
        System.out.println("It's nik.dany branch.");
    }

    public static void StartTest()
    {
        Bill b = new Bill(123,
                new Salesman("kolya", "login", "pass"),
                0,
                new BillTime(2017, 11, 10,21, 56));

        b.addProduct(new Product("orange", 12, 345678));
        b.addProduct(new Product("apple", 13, 34567778));
        b.addProduct(new Product("soap", 23, 99867778));

        b.closeBill();

        b.addProduct(new Product("SOAAAAP", 23, 99867778));

        b.printBill();
    }
}
