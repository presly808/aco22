package main.java;


/**
 * Created by ENIAC on 05.11.2017.
 */
public class Task {

    public static void main(String[] args) {

        Product product1 = new Product("Макарошки", 40, "#0001");
        Product product2 = new Product("Пюрешечка", 50, "#0002");
        Product product3 = new Product("Котлетки", 100, "#0003");

        Product[] testProduct = new Product[3];

//        Bill testBill = new Bill(testProduct, Bill., saller, time);
//
//
//        testBill.add(product1);
//        testBill.add(product2);
//        testBill.add(product3);


        System.out.println(product1.printFullInfo());
//       String testString = testBill.makeBill();
//        System.out.println(testString);

//        System.out.println(product1.formatProduct());
//        System.out.println(product2.formatProduct());
//        System.out.println(product3.formatProduct());


    }


}
