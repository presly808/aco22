package artcode.shop.terminal;

import artcode.shop.Bill;
import artcode.shop.Product;
import artcode.shop.Salesman;


public class Terminal {
    private Bill[] bills;
    private Salesman[] sales;

    private static int countClosedBill;
    private static int countCreatedBill;

    private static int countSalesman;


    public void login(Salesman salesman) {
        if (salesman != null)
            if (getSales() == null) {
            int size = 20;
            sales = new Salesman[size];
        }

        getSales()[countSalesman++] = salesman;
    }

    public Bill createBill(Salesman salesman) {
        if (getCountClosedBill() < getCountCreatedBill()) return bills[countCreatedBill];

        if ((salesman == null)) return null;
        else {
            for (Salesman seller : this.getSales()) {
                if (seller != null) {

                    if (seller.equals(salesman)) {
                        setCountCreatedBill(getCountCreatedBill() + 1);
                        return new Bill();
                    }
                }
            } return null;
        }
    }

    public Bill addProduct() {
        for (Bill bill : getBills()) {
            if (!bill.isClosed()) return bill;
        }
        return null;
    }

    public void closeAndSaveBill (Bill bill) {
        if (bill != null) {
            if (bills == null) {
                int size = 20;
                bills = new Bill[size];
            }
            if (!bill.isClosed()) {
                bill.closeBill();
                bills[countClosedBill++] = new Bill();
            }
        }
     }

    public Bill findBillById(int id) {
        if (id > 0) {
            if (getBills() != null) {
                for (Bill bill : getBills()) {
                    if (id == bill.getId()) {
                        return bill;
                    }
                }
            }
        }
        return null;
    }

    public Salesman findSalesmanByLoginOrFullname (String nameOrLogin) {
        if (nameOrLogin != null) {
            if (getSales() != null) {
                for (Salesman salesman : getSales()) {
                    if (nameOrLogin.equals(salesman.getFullName()) || nameOrLogin.equals(salesman.getLogin())) {
                        return salesman;
                    }
                }
            }
        }
        return null;
    }

    public Salesman getTopNofSalesMan () {
        Salesman bestSeller = null;
        if (getSales() != null && getSales().length > 0) {
            int[] countSales = new int[getSales().length];
            for (int i = 0; i < getSales().length; i++) {
                int count = 0;
                for (Bill bill : getBills()) {
                    if (getSales()[i].equals(bill.getSalesman())) {
                        count++;
                    }
                }
                countSales[i] = count;
            }
             bestSeller = getSales()[maxIndex(countSales)];
        }
        return bestSeller;
    }

    private int maxIndex(int[] arr) {
        int ind = 0;
        if (arr != null) {
            int max = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                    ind = i;
                }
            }
        }
        return ind;
    }

    public void doSomeStatisticStuff () {

    }

    private double getMaxAmountPrice () {
        double max = 0.0;
        if (getBills() != null && getBills().length > 0) {
            for (Bill bill : getBills()) {
                if (bill.getAmountPrice() > max)
                    max = bill.getAmountPrice();
            }
        }
        return max;
    }

    private double getMinAmountPrice () {
        double min = 0.0;
        if (getBills() != null && getBills().length > 0) {
            min = getBills()[0].getAmountPrice();
            for (Bill bill : getBills()) {
                if (bill.getAmountPrice() < min)
                    min = bill.getAmountPrice();
            }
        }
        return min;
    }

    private double getAverageAmountPrice () {
        double average = 0.0;
        if (getBills() != null && getBills().length > 0) {
            for (Bill bill : getBills()) {
                average += bill.getAmountPrice();
            }
        }
        return average/getBills().length;
    }

    private int getMaxProduct() {
        int max = 0;
        if (getBills() != null && getBills().length > 0) {
            max = ( getBills()[0].getProducts() != null) ? getBills()[0].getProducts().length : max;
            for (Bill bill : getBills()) {
                if (bill.getProducts() != null && bill.getProducts().length > max)
                    max = bill.getProducts().length;
            }
        }
        return max;
    }

    private int getMinProduct() {
        return 0;
    }

    private int getAverageProduct() {
        return 0;
    }

    private int getMaxProductSales() {
        return 0;
    }

    private int getMinProductSales() {
        return 0;
    }

    private int getAverageProductSales() {
        return 0;
    }

    private int getAverageAmountPriceSales() {
        return 0;
    }

    private int getMaxAmountPriceSales() {
        return 0;
    }


    public static void setCountClosedBill(int countClosedBill) { Terminal.countClosedBill = countClosedBill;    }

    public static int getCountCreatedBill() {
        return Terminal.countCreatedBill;
    }
    public static int getCountClosedBill() {
        return Terminal.countClosedBill;
    }

    public static void setCountCreatedBill(int countCreatedBill) {
        Terminal.countCreatedBill = countCreatedBill;
    }

    public Terminal() {
    }

    public Bill[] getBills() {
        return bills;
    }

    public Salesman[] getSales() {
        return sales;
    }

}
