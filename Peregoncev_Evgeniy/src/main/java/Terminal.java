import java.util.Scanner;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class Terminal {

    private Bill[] bils;
    private Salesman[] sales;

    private int billCountSize;
    private int salesCountSize;

    private int currentSallerIndex = -1;

    public Terminal() {

    }

    public int getCurrentSallerIndex() {
        return currentSallerIndex;
    }

    public void setCurrentSallerIndex(int currentSallerIndex) {
        this.currentSallerIndex = currentSallerIndex;
    }

    public Terminal(Bill[] bils, Salesman[] sales) {
        this.bils = bils;
        this.sales = sales;
    }

    public Bill[] getBils() {
        return bils;
    }

    public void setBils(Bill[] bils) {
        this.bils = bils;
    }

    public Salesman[] getSales() {
        return sales;
    }

    public void setSales(Salesman[] sales) {
        this.sales = sales;
    }

    public int getBillCountSize() {
        return billCountSize;
    }

    public void setBillCountSize(int billCountSize) {
        this.billCountSize = billCountSize;
    }

    public int getSalesCountSize() {
        return salesCountSize;
    }

    public void setSalesCountSize(int salesCountSize) {
        this.salesCountSize = salesCountSize;
    }


    //Methods
    public boolean login(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            System.out.println("write true login/pass");
            return false;
        }
        if (sales == null) {
            System.out.println("dd");
            return false;
        }

        for (int i = 0; i < sales.length; i++) {
            if (sales[i].getLogin().equals(login) &&
                    sales[i].getPass().equals(pass)) {
                System.out.println("Hello "+sales[i].getFullname());
                setCurrentSallerIndex(i);
            }
        }
        return false;
    }

    public Bill createbill(Bill bill) {
        if ((bill == null) || (bils == null))
            return null;
        if (currentSallerIndex == -1)
            return null;

        bils[billCountSize++] = bill;
        bill.setBillId(billCountSize + 1);
        return bill;
    }

    public void closeAndSaveBill(Bill bill) {
        if ((bill == null) || (bils == null)) {

        } else {
            bill.setIsclosed(true);

        }
    }

    public Bill findBillById(Bill[] bill, Bill currentBill, int billId) {

        if (bill == null || billCountSize == 0) return null;

        for (int i = 0; i < billCountSize; i++) {
            if (currentBill.getBillId() == billId)
                return currentBill;
        }
        return currentBill;
    }

    public Salesman findSalesmanByLogin(Salesman[] sales, Salesman searchLogin) {
        if ((sales == null) || (searchLogin == null)) {
        } else {
            for (int i = 0; i < sales.length; i++) {
                if (sales[i].getLogin() == searchLogin.getLogin()) {
                    return sales[i];
                }
            }
        }
        return null;
    }

}

//    public boolean addproduct(Bill[] bill, Product product) {
//        boolean isContains = false;
//
//        bill[billCountSize++].addProduct(product);
//
//        return false;
//    }
