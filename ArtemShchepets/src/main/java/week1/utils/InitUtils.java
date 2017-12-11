package week1.utils;

import week1.database.IAppDB;
import week1.database.IAppDBImpl;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitUtils {


    public static IAppDB initSellerDb() {

        IAppDB db = new IAppDBImpl();

        db.getAllSellers().add(new Seller(
                "worker", "password", "Nadya Horoshun"));
        db.getAllSellers().add(new Seller(
                "worker123", "password11", "Vasya Noob"));
        db.getAllSellers().add(new Seller(
                "worker22", "password432", "Annita Volosova"));
        db.getAllSellers().add(new Seller(
                "worker01", "password1111", "Vova Split"));

        return db;
    }

    public static IAppDB initDb() {

        IAppDB db = new IAppDBImpl();

        db.getAllSellers().add(new Seller(
                "worker", "password", "Nadya Horoshun"));
        db.getAllSellers().add(new Seller(
                "worker123", "password11", "Vasya Noob"));
        db.getAllSellers().add(new Seller(
                "worker22", "password432", "Annita Volosova"));
        db.getAllSellers().add(new Seller(
                "worker01", "password1111", "Vova Split"));

        db.getAllBills().add(new Bill(0, 22.33, LocalDateTime.now(), false));
        db.getAllBills().add(new Bill(1, 154.4, LocalDateTime.now(), true));
        db.getAllBills().add(new Bill(2, 9.2, LocalDateTime.now(), false));
        db.getAllBills().add(new Bill(3, 13.55, LocalDateTime.now(), false));
        db.getAllBills().add(new Bill(4, 10.0, LocalDateTime.now(), true));
        db.getAllBills().add(new Bill(5, 225.5, LocalDateTime.now(), false));

        return db;
    }

    public static Seller createDepartment() {

        Seller rootSeller = new Seller("main_worker", "main_password",
                "MainWorker", generateSimpleBillList(12));

        rootSeller.addSubSeller(new Seller("worker1", "password1",
                "Worker1", generateSimpleBillList(6)));
        rootSeller.addSubSeller(new Seller("worker2", "password2",
                "Worker2", generateSimpleBillList(16)));
        rootSeller.addSubSeller(new Seller("worker3", "password3",
                "Worker3", generateSimpleBillList(5)));

        rootSeller.getSubsellers().get(0).addSubSeller(new Seller("worker4", "password4",
                "Worker4", generateSimpleBillList(22)));
        rootSeller.getSubsellers().get(0).addSubSeller(new Seller("worker5", "password5",
                "Worker5", generateSimpleBillList(2)));

        rootSeller.getSubsellers().get(1).addSubSeller(new Seller("worker6", "password6",
                "Worker6", generateSimpleBillList(14)));
        rootSeller.getSubsellers().get(1).addSubSeller(new Seller("worker7", "password7",
                "Worker7", generateSimpleBillList(6)));

        rootSeller.getSubsellers().get(2).addSubSeller(new Seller("worker8", "password8",
                "Worker8", generateSimpleBillList(10)));
        rootSeller.getSubsellers().get(2).addSubSeller(new Seller("worker9", "password9",
                "Worker9", generateSimpleBillList(30)));

        return rootSeller;
    }

    private static List<Bill> generateSimpleBillList(int quantityOfBills) {

        List<Bill> bills = new ArrayList<>();

        for (int i = 0; i < quantityOfBills; i++) {
            bills.add(generateSimpleBill());
        }

        return bills;
    }

    private static Bill generateSimpleBill() {

        Bill bill = new Bill();
        Product testProduct = new Product("testProduct", 1.00);

        for (int i = 0; i < 5; i++) {
            bill.getProductList().add(testProduct);
        }

        return bill;
    }

}
