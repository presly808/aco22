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

    public static List<Bill> staticFilter(AppDB appDB,
                                          List<Salesman> salesmen,
                                          List<Product> products,
                                          LocalDateTime startTime,
                                          LocalDateTime endTime,
                                          Comparator<Bill> comparator) {

        List<Bill> resBill = new ArrayList<>();

        List<Bill> bills = appDB.getAllBills();

        for (int i = 0; i < bills.size(); i++) {

            if (!bills.get(i).isClosed()) continue;

            if (salesmen != null && !bills.get(i).hasSalesman(salesmen)) {
                continue;
            }

            if (products != null && !bills.get(i).hasProducts(products)) {
                continue;
            }

            if (startTime != null && bills.get(i).getCloseTime().
                    compareTo(startTime) < 0) {
                continue;
            }

            if (endTime != null && bills.get(i).getCloseTime().
                    compareTo(endTime) > 0) {
                continue;
            }

            resBill.add(bills.get(i));

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

    public static double sumSalarySalesmen(AppDB appDB) {

        List<Bill> bills = staticFilter(appDB, appDB.getAllSalesman(),
                null, null, null, new BillIdComparator());

        for (Salesman salesman : appDB.getAllSalesman()) {
            salesman.setSoldProducts(0);
            salesman.setAmountSales(0);
        }

        for (Bill bill : bills) {
            Salesman salesman = bill.getSalesMan();
            salesman.setSoldProducts(
                    salesman.getSoldProducts() +
                            bill.getProducts().size());

            salesman.setAmountSales(salesman.getAmountSales() +
                    bill.getAmountPrice());
        }

        createBinaryTree(appDB);

        countSalary(appDB.getAllSalesman().get(0));

        return sumSalary(appDB.getAllSalesman().get(0));
    }

    public static double countSalary(Salesman salesman) {

        salesman.setSalary(salesman.getAmountSales() * 0.05);

        for (Salesman subSalesman : salesman.getSubSalesmen()) {
            salesman.setSalary(salesman.getSalary() +
                    0.02 * countSalary(subSalesman));
        }

        return salesman.getSalary();
    }

    public static double sumSalary(Salesman salesman) {

        double sum = salesman.getSalary();

        if (salesman.getSubSalesmen().size() == 0) {
            return sum;
        }

        for (Salesman subSalesman : salesman.getSubSalesmen()) {
            sum += sumSalary(subSalesman);
        }

        return sum;
    }

}
