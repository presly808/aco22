public class TestBill {
    public static void main(String[] args) {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        Salesman s1 = new Salesman("111","222","333");

        Bill b = new Bill(1,s1,10);

        p1.name = "Хлеб";
        p1.price = 10.50;
        p1.id = 111;

        b.addProduct(p1);

        p2.name = "Масло";
        p2.price = 70.30;
        p2.id = 222;

        b.addProduct(p2);

        p3.name = "Молоко";
        p3.price = 15.50;
        p3.id = 333;

        b.addProduct(p3);

        b.closeBill();

        System.out.println(b.printBill());

    }
}
