public class TestBill {

    public static final int DEFAULT_AMOUNT_PRODUCTS = 100;
    public static final int DEFAULT_AMOUNT_SALESMEN = 20;

    public static void main(String[] args) {


        Product[] p = new Product[DEFAULT_AMOUNT_PRODUCTS];

        for (int i=0; i<p.length; i++) {
            p[i] = Utils.generateProduct();
        }

        Salesman[] s = new Salesman[DEFAULT_AMOUNT_SALESMEN];

        for (int i=0; i<s.length; i++) {
            s[i] = Utils.generateSalesman();
        }

        Terminal t = new Terminal(s);


        Bill b = new Bill(s1, 10);

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

        b = new Bill(s1, 10);
        b.addProduct(p3);

        b.closeBill();

        System.out.println(b.printBill());


    }
}
