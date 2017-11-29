package ua.artcode.market.utils;

import ua.artcode.market.DataBases.AppDB;
import ua.artcode.market.models.*;

public class Utils {


    public static Product generateProduct(AppDB appDB) {

        String[] s = {"Meat", "Fish", "Fruit", "Vegetable", "Sausage"};

        return new Product(s[(int) (Math.random() * s.length)] +
                (int) (Math.random() * appDB.getAllProducts().size()),
                (Math.random() * 1000));
    }

    public static Salesman generateSalesman(AppDB appDB) {

        String[] s = {"Andry", "Kate", "Sveta", "Igor", "Maxim", "Olya"};

        int index = (int) (Math.random() * s.length);

        String fullname = s[index] +
                (int) (Math.random() * appDB.getAllSalesman().size());

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


}
