package week1.controller;

import week1.model.Bill;
import week1.model.Product;

import java.util.List;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class ITerminalControllerControllerImpl implements ITerminalController {

    private IAppDb iAppDb;

    public ITerminalControllerControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    private int billCountSize;
    private int salesCountSize;

    private int currentSallerIndex = -1;


    public int getCurrentSallerIndex() {
        return currentSallerIndex;
    }

    public void setCurrentSallerIndex(int currentSallerIndex) {
        this.currentSallerIndex = currentSallerIndex;
    }

    public ITerminalControllerControllerImpl() {

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
        if (iAppDb == null) {
            System.out.println("wrong salesman database");
            return false;
        }

//        for (int i = 0; i < salesCountSize; i++) {
//            if (sales[i].getLogin().equals(login) &&
//                    sales[i].getPass().equals(pass)) {
//                System.out.println("Hello " + sales[i].getFullname());
//                setCurrentSallerIndex(i);
//            }
//        }
           if (currentSallerIndex == -1){
            System.out.println("wrong log/pass");
           }


        return false;
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        Bill billWithId = iAppDb.saveBill(bill);

        return billWithId;
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        Bill bill = iAppDb.findByBillId(billId);

        if(bill == null){
            System.out.println("no bill found");
            return null;
        }

        boolean add = bill.getProductList().add(product);

        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getAllBills();
    }

    @Override
    public Bill closeBill(int id) {
        Bill bill = iAppDb.findByBillId(id);
        bill.setIsclosed(true);
        bill.getTime().setCloseTime(bill.getTime().printTime());

        iAppDb.update(bill);


        return bill;
    }






}
