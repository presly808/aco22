package artcode.shop.terminal;

import artcode.shop.bill.Bill;
import artcode.shop.creator.BillCreator;
import artcode.shop.creator.SalesmanCreator;
import artcode.shop.product.Product;
import artcode.shop.salesman.Salesman;




public class Terminal {
    private static final int DEFAULT_SIZE = 5;

    private int countClosedBill = 0;
    private int countCreatedBill = 0;

    private int countSalesman = 0;


    private Bill[] bills;
    private Salesman[] sales;

    public Terminal() {
    }

    public Terminal(Bill[] bills, Salesman[] sales) {
        this.bills = bills;
        this.sales = sales;
    }

    public int getCountCreatedBill() {
        return this.countCreatedBill;
    }
    public int getCountClosedBill() {
        return this.countClosedBill;
    }

    public void setCountCreatedBill(int countCreatedBill) {
        this.countCreatedBill = countCreatedBill;
    }

    public Bill[] getBills() {
        return bills;
    }

    public Salesman[] getSales() {
        return sales;
    }

    public void setSales(Salesman[] sales) {
        this.sales = sales;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public boolean login(Salesman salesman) {
        if (salesman != null)

            if (this.getSales() == null) {
                this.sales = new Salesman[DEFAULT_SIZE];
                this.sales[countSalesman] = salesman;
                return true;
            }
        boolean isAtSales = false;
        for (Salesman seller : this.getSales()) {
            if (salesman.equals(seller)){
                isAtSales = true;
                break;
            }
        } if (!isAtSales) {
            this.sales[countSalesman] = salesman;
            this.countSalesman++;
            return true;
        }

        return true;
    }

    public boolean equals(Terminal terminal) {
        if (terminal == null) return false;
        if (this.getSales().length != terminal.getSales().length) return  false;
        if (this.getBills().length != terminal.getBills().length) return  false;
        for (int i = 0; i < terminal.getSales().length; i++) {
            if (terminal.getSales()[i] != null && !terminal.getSales()[i].equals(this.getSales()[i])) return false;
        }
        for (int i = 0; i < terminal.getBills().length; i++) {
            if (terminal.getBills()[i] != null && !terminal.getBills()[i].equals(this.getBills()[i])) return false;
        }

        return true;
    }
    public Bill createBill(Salesman salesman) {
        if (this.getCountClosedBill() < this.getCountCreatedBill()) {
            Bill bill = this.getBillBySalesman(salesman, this);
            bills[bill.getAddedProducts()] = bill;
            bill.setAddedProducts(bill.getAddedProducts() + 1);
            return bill;
        }

        if ((salesman != null)) {
            for (Salesman seller : this.getSales()) {
                 if (seller.equals(salesman)) {
                        Bill bill = BillCreator.createBill(salesman);
                        bills[bill.getAddedProducts()] = bill;
                        bill.setAddedProducts(bill.getAddedProducts() + 1);
                        return bill;
                }
            }
        }
        return null;
    }

    private Bill getBillBySalesman(Salesman salesman, Terminal terminal) {
        if (terminal != null && salesman != null) {
            for (Bill bill : terminal.getBills()) {
                if (bill.getSalesman().equals(salesman) && !bill.isClosed()) {
                    return bill;
                }
            }
        } return null;
    }

    public Bill addProduct(Product product) {
        if (this.getBills() == null) {
            bills = new Bill[DEFAULT_SIZE];
        } else {
            for (Bill bill : getBills()) {
                if (bill != null && !bill.isClosed()) {
                    return bill;
                }
            }
        }
        return null;
    }
//_________________

    public boolean closeAndSaveBill (Bill bill) {
        if (bill != null) {
            if (this.bills != null) {
                if (!bill.isClosed()) {
                    double amountPrice = bill.closeBill();
                    bill.setAmountPrice(amountPrice);
                    this.printBill(bill);
                    return true;

                }
            } return false;
        } return false;
    }

    private void printBill(Bill bill) {
        bill.printBill();
    }







    public Bill findBillById(int id) {
        if (id > 0) {
            if (this.getBills() != null) {
                for (Bill bill :this. getBills()) {
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
            if (this.getSales() != null) {
                for (Salesman salesman : this.getSales()) {
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
        if (this.getSales() != null && this.getSales().length > 0) {
            int[] countSales = new int[getSales().length];
            for (int i = 0; i < this.getSales().length; i++) {
                int count = 0;
                for (Bill bill : this.getBills()) {
                    if (this.getSales()[i].equals(bill.getSalesman())) {
                        count++;
                    }
                }
                countSales[i] = count;
            }
             bestSeller = this.getSales()[maxIndex(countSales)];
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
        if (getBills() != null && this.getBills().length > 0) {
            for (Bill bill : this.getBills()) {
                if (bill.getAmountPrice() > max)
                    max = bill.getAmountPrice();
            }
        }
        return max;
    }

    private double getMinAmountPrice () {
        double min = 0.0;
        if (getBills() != null && this.getBills().length > 0) {
            min = this.getBills()[0].getAmountPrice();
            for (Bill bill : this.getBills()) {
                if (bill.getAmountPrice() < min)
                    min = bill.getAmountPrice();
            }
        }
        return min;
    }

    private double getAverageAmountPrice () {
        double average = 0.0;
        if (getBills() != null && this.getBills().length > 0) {
            for (Bill bill : this.getBills()) {
                average += bill.getAmountPrice();
            }
        }
        return average/getBills().length;
    }

    private int getMaxProduct() {
        int max = 0;
        if (this.getBills() != null && this.getBills().length > 0) {
            max = ( this.getBills()[0].getProducts() != null) ? this.getBills()[0].getProducts().length : max;
            for (Bill bill : this.getBills()) {
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


    public void setCountClosedBill(int countClosedBill) { this.countClosedBill = countClosedBill;    }


}
