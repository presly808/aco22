package week1.database;

import week1.interfaces.IAppDB;
import week1.model.Bill;
import week1.model.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IAppDBImpl implements IAppDB {

    private final static Logger logger = Logger.getLogger(IAppDBImpl.class.getName());

    private int billNextId;

    private int currentSeller = -1;

    private List<Bill> bills;
    private List<Seller> sellers;

    public IAppDBImpl() {
        this.bills = new ArrayList<>();
        this.sellers = new ArrayList<>();
        logger.setLevel(Level.OFF);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public List<Bill> getAllBills() {
        return bills;
    }

    @Override
    public List<Seller> getAllSellers() {
        return sellers;
    }

    @Override
    public int getCurrentSellerId() {
        return this.currentSeller;
    }

    @Override
    public void setCurrentSeller(int currentSellerId) {
        this.currentSeller = currentSellerId;
    }

    @Override
    public Bill findByBillId(int billId) {

        for (Bill bill : bills) {
            if (billId == bill.getId()) {

                logger.info("Bill was founded in array list!");
                return bill;
            }
        }

        logger.info("Bill wasn't founded!");
        return null;
    }

    @Override
    public Seller findBySellerLoginOrFullName(String loginOrFullName) {

        for (Seller seller : sellers) {
            if (loginOrFullName.equals(seller.getLogin())
                    || loginOrFullName.equals(seller.getFullName())) {

                logger.info("Seller was founded");
                return seller;
            }
        }

        logger.info("Seller wasn't founded!");
        return null;
    }

    @Override
    public Seller findBySellerLoginAndPassword(String login, String password) {

        for (Seller seller : sellers) {
            if (login.equals(seller.getLogin())
                    && password.equals(seller.getPassword())) {

                logger.info("Seller was founded");
                return seller;
            }
        }

        logger.info("Seller wasn't founded!");
        return null;
    }

    @Override
    public Bill saveBill(Bill bill) {

        bill.setId(billNextId++);
        bills.add(bill);

        logger.info("Bill was added to arrayList");
        return bill;
    }

    @Override
    public Seller saveSeller(Seller seller) {

        sellers.add(seller);

        logger.info("Seller was added to arrayList");
        return seller;
    }

    @Override
    public Bill removeBill(int billId) {

        Bill found = findByBillId(billId);

        if (found == null) {
            logger.warning("Not found bill with such id");
            return null;
        }

        bills.remove(found);

        logger.info("Bill was removed.");
        return found;
    }

    @Override
    public Seller removeSeller(String login) {

        Seller found = findBySellerLoginOrFullName(login);

        if (found == null) {
            logger.warning("Not found seller with such login");
            return null;
        }

        sellers.remove(found);

        logger.info("Seller was removed.");
        return found;
    }

    @Override
    public Seller updateSeller(Seller seller) {
        int index = sellers.indexOf(seller);

        if (index == -1) {
            logger.warning("Not found bill with such id");
            return null;
        }

        logger.info("Seller was updated.");
        return sellers.set(index, seller);
    }

    @Override
    public Bill updateBill(Bill bill) {

        int index = bills.indexOf(bill);

        if (index == -1) {
            logger.warning("Not found bill with such id");
            return null;
        }

        logger.info("Bill was updated.");
        return bills.set(index, bill);
    }
}
