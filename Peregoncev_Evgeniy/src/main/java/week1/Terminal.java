package week1;

import week1.Bill;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class Terminal {

    private Bill[] bils;
    private Salesman[] sales;

    private int billCountSize;
    private int salesCountSize;

    private int currentSallerIndex = -1;


    public int getCurrentSallerIndex() {
        return currentSallerIndex;
    }

    public void setCurrentSallerIndex(int currentSallerIndex) {
        this.currentSallerIndex = currentSallerIndex;
    }

    public Terminal() {
        this.bils = new Bill[10];
        this.sales = new Salesman[10];
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

        for (int i = 0; i < salesCountSize; i++) {
            if (sales[i].getLogin().equals(login) &&
                    sales[i].getPass().equals(pass)) {
                System.out.println("Hello " + sales[i].getFullname());
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

        bils[billCountSize] = bill;
        bill.setBillId(++billCountSize);
        return bill;
    }

    public void closeAndSaveBill(Bill bill) {
        if ((bill == null) || (bils == null)) {
            System.out.println("there is no one bill to save");

        } else {
            bill.setIsclosed(true);
            bill.getTime().closeTime = bill.getTime().printTime();

        }
    }

    public Bill findBillById(Bill[] bill, int billId) {
        if (bill == null || billCountSize == 0) {
            System.out.println("there is no one bill to search ");
        } else {
            for (int i = 0; i < billCountSize; i++) {
                if (bill[i].getBillId() == billId) {
                    return bill[i];
                }
            }
        }
        return null;
    }

    public Salesman findSalesmanByLogin(Salesman[] sales, String searchLogin) {
        if ((sales == null) || (searchLogin == null)) {
            System.out.println("there is no salesman to search");
        } else {
            for (int i = 0; i < salesCountSize; i++) {
                if (sales[i].getLogin() == searchLogin) {
                    return sales[i];
                }
            }
        }
        return null;
    }

}
