package ua.artcode.market.utils;

import ua.artcode.market.comparators.BillIdComparator;
import ua.artcode.market.databases.AppDB;
import ua.artcode.market.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ua.artcode.market.databases.AppDB.*;

public class Utils {


    public static Product generateProduct() {

        String[] s = {"Meat", "Fish", "Fruit", "Vegetable", "Sausage"};

        return new Product(s[(int) (Math.random() * s.length)] +
                (int) (Math.random() * countProducts),
                (Math.random() * 1000));
    }

    public static Salesman generateSalesman() {

        String[] s = {"Andry", "Kate", "Sveta", "Igor", "Maxim", "Olya"};

        int index = (int) (Math.random() * s.length);

        String fullname = s[index] +
                (int) (Math.random() * countSalesman);

        String login = s[index];

        String password = "";

        for (int i = 1; (int) i < Math.random() * 10 + 7; i++) {
            password = password + (int) (Math.random() * 10);
        }

        return new Salesman(fullname, login, password);
    }

    public static double getMax(AppDB appDB) {

        if (appDB.getAllBills().size() == 0) return 0;

        double max = appDB.getAllBills().get(0).getAmountPrice();

        for (int i = 1; i < appDB.getAllBills().size(); i++) {
            if (max < appDB.getAllBills().get(i).getAmountPrice()) {
                max = appDB.getAllBills().get(i).getAmountPrice();
            }
        }

        return max;

    }

    public static double getMin(AppDB appDB) {

        if (appDB.getAllBills().size() == 0) return 0;

        double min = appDB.getAllBills().get(0).getAmountPrice();


        for (int i = 1; i < appDB.getAllBills().size(); i++) {
            if (min > appDB.getAllBills().get(i).getAmountPrice()) {
                min = appDB.getAllBills().get(i).getAmountPrice();
            }
        }

        return min;
    }

    public static double getAverage(AppDB appDB) {

        if (appDB.getAllBills().size() == 0) return 0;

        double result = appDB.getAllBills().get(0).getAmountPrice();

        for (int i = 1; i < appDB.getAllBills().size(); i++) {
            result += appDB.getAllBills().get(i).getAmountPrice();
        }

        result /= appDB.getAllBills().size();

        return result;
    }

    public static int countSoldProducts(AppDB appDB) {

        int result = 0;

        for (int i = 0; i < appDB.getAllBills().size(); i++) {
            result += appDB.getAllBills().get(i).getProducts().size();

        }

        return result;
    }

    public static List<Bill> filter(AppDB appDB,
                                    List<Salesman> salesmen,
                                    List<Product> products,
                                    LocalDateTime startTime,
                                    LocalDateTime endTime,
                                    Comparator<Bill> comparator) {

        List<Bill> resBill = new ArrayList<>();

        for (Salesman salesman : salesmen) {
            salesman.setSoldProducts(0);
            salesman.setAmountSales(0);
        }

        Salesman salesman = null;

        int index = 0;

        List<Bill> bills = appDB.getAllBills();

        for (int i = 0; i < bills.size(); i++) {

            if (!bills.get(i).isClosed()) continue;

            boolean addBill = true;

            if (salesmen != null) {

                addBill = false;

                for (int j = 0; j < salesmen.size(); j++) {
                    if (bills.get(i).getSalesMan().
                            equals(salesmen.get(j))) {
                        addBill = true;
                        salesman = bills.get(i).getSalesMan();
                        break;
                    }
                }
            }

            if (addBill && products != null) {

                addBill = false;

                if (bills.get(i).hasProducts(products)) {
                    addBill = true;
                } else {
                    addBill = false;
                }
            }

            if (addBill && startTime != null) {
                if (bills.get(i).getCloseTime().
                        compareTo(startTime) >= 0) {
                    addBill = true;
                } else {
                    addBill = false;
                }
            }

            if (addBill && endTime != null) {
                if (bills.get(i).getCloseTime().
                        compareTo(endTime) <= 0) {
                    addBill = true;
                } else {
                    addBill = false;
                }
            }

            if (addBill) {
                resBill.add(bills.get(i));
                salesman.setSoldProducts(
                        salesman.getSoldProducts() +
                                bills.get(i).getProducts().size());

                salesman.setAmountSales(salesman.getAmountSales() +
                        bills.get(i).getAmountPrice());
            }

            resBill.sort(comparator);

        }

        return resBill;
    }

    public static void createBinaryTree(AppDB appDB) {

        List<Salesman> salesmen = appDB.getAllSalesman();

        int index = 0;
        int childNodes = 2;

        outer:
        for (int i = 0; i < salesmen.size(); i++) {
            for (int j = 1; j <= childNodes; j++) {
                index = childNodes * i + j;
                if (index < salesmen.size()) {
                    salesmen.get(i).getSubSalesmen().add(salesmen.get(index));
                } else {
                    break outer;
                }
            }
        }
    }

    public static void countSalarySalesman(AppDB appDB) {

        List<Bill> bills = filter(appDB, appDB.getAllSalesman(),
                null, null, null, new BillIdComparator());

        createBinaryTree(appDB);

        for (Salesman salesman : appDB.getAllSalesman()) {
            for (Salesman subSalesman : salesman.getSubSalesmen()) {

            }
        }



    }


}
