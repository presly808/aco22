package week1.controller;

import week1.database.IAppDB;
import week1.exceptions.*;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.SalesStatistic;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static week1.utils.TerminalStatisticUtils.calculateSumOfSoldProducts;
import static week1.utils.TerminalStatisticUtils.findMaxPriceBill;
import static week1.utils.TerminalStatisticUtils.findMinPriceBill;

public class ITerminalControllerImpl implements ITerminalController {

    private final static Logger logger = Logger.getLogger(ITerminalControllerImpl.class.getName());

    private IAppDB iAppDB;

    private int currentSeller = -1;

    public ITerminalControllerImpl(IAppDB iAppDB) {
        this.iAppDB = iAppDB;
        this.currentSeller = iAppDB.getAllSellers().size() - 1;
        logger.setLevel(Level.OFF);
    }

    @Override
    public boolean login(String login, String password) throws UnableToLogInException {

        // set current seller in db to "-1", logging out
        currentSeller = -1;

        if ((login == null || password == null) ||
                (login.equals("") || password.equals(""))
                || iAppDB.getAllSellers().size() == 0)
            throw new UnableToLogInException("Invalid log\\pass or aren't any sellers!");

        int sellerId = iAppDB.getAllSellers().
                indexOf(iAppDB.findBySellerLoginAndPassword(login, password));

        //TODO HOW TO DO EXCEPTION THERE CORRECTLY?
        if (sellerId == -1) {
            logger.warning("There aren't any sellers with such login\\pass!");
            return false;
        }

        currentSeller = sellerId;

        logger.info("Successfully logged in!");
        return true;
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        if (currentSeller != -1)
            bill.setSeller(iAppDB.getAllSellers().get(currentSeller));

        Bill billWithId = iAppDB.saveBill(bill);

        logger.info("Method createBill done.");
        return billWithId;
    }

    @Override
    public Bill addProduct(int billId, Product product) throws UnableToFindABillException, InvalidBillIdException {

        if (billId <= -1) throw new InvalidBillIdException("Bill id is -1 or less!");

        Bill bill = iAppDB.findByBillId(billId);

        if (bill == null) throw new UnableToFindABillException("There isn't a bill with such id!");

        product.setId(bill.getNextProductId());
        bill.getProductList().add(product);
        bill.setNextProductId(bill.getNextProductId() + 1);
        bill.setAmountPrice(bill.getAmountPrice() + product.getPrice());

        iAppDB.updateBill(bill);

        logger.info("Method addProduct done");
        return bill;
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
    public List<Bill> getAllBills() {
        return iAppDB.getAllBills();
    }

    @Override
    public Bill closeBill(int billId) throws UnableToFindABillException {

        Bill bill = iAppDB.findByBillId(billId);

        if (bill == null) throw new UnableToFindABillException("There isn't a bill with such id!");

        bill.setClosed(true);
        bill.setCloseTime(LocalDateTime.now());

        iAppDB.updateBill(bill);

        logger.info("Bill was closed!");
        return bill;
    }

    @Override
    public Bill findBillById(int billId) throws InvalidBillIdException {

        if (billId <= -1) throw new InvalidBillIdException("Bill id is -1 or less!");

        logger.info("Method findBillById was done.");
        return iAppDB.findByBillId(billId);
    }

    @Override
    public Seller findSellerByLoginOrFullName(String loginOrFullName) {

        logger.info("Method findSellerBy... was done.");
        return iAppDB.findBySellerLoginOrFullName(loginOrFullName);
    }

    @Override
    public Seller getTopOfSalesman() throws UnableToGetTopSellersException {

        if (iAppDB.getAllSellers().isEmpty() || iAppDB.getAllBills().isEmpty())
            throw new UnableToGetTopSellersException("There aren't any sellers or bills in DB!");

        return iAppDB.getAllSellers().stream().peek(this::calculateSellerSoldProducts)
                .peek(iAppDB::updateSeller)
                .max(Comparator.comparing(Seller::getSoldProducts)).get();
    }

    private Seller calculateSellerSoldProducts(Seller seller) {

        //TODO HOW TO APPLY JAVA8 HERE?
        for (Bill bill : iAppDB.getAllBills()) {
            if (seller.getFullName() != null && seller.getSoldProducts() == 0) {
                if (seller.getFullName().equals(bill.getSeller().getFullName()))
                    seller.setSoldProducts(seller.getSoldProducts() + bill.getProductList().size());
            }
        }

        logger.info("Seller sold products were calculated.");
        return seller;
    }

    @Override
    public SalesStatistic doSomeStatisticStuff() throws UnableToDoStatisticException, UnableToGetTopSellersException {

        SalesStatistic salesStatistic = new SalesStatistic();

        if (iAppDB.getAllSellers().size() == 0 || iAppDB.getAllBills().size() == 0)
            throw new UnableToDoStatisticException("There aren't any sellers or bills in DB!");

        salesStatistic.setMaxBillPrice(findMaxPriceBill(iAppDB));
        salesStatistic.setMinBillPrice(findMinPriceBill(iAppDB));
        salesStatistic.setSoldProducts(calculateSumOfSoldProducts(iAppDB));
        salesStatistic.setBestSalesMan(getTopOfSalesman());

        logger.info("Method doStatisticStuff was done.");
        return salesStatistic;
    }

    @Override
    public List<Bill> filter(LocalDateTime startTime, LocalDateTime endTime, Comparator<Bill> comparator)
            throws UnableToFilterException {

        if (endTime.compareTo(startTime) < 0) throw new UnableToFilterException("Invalid end and start dates!");

        return iAppDB.getAllBills().stream().filter(bill ->
                bill.getOpenTime().compareTo(startTime) > 0
                        && bill.getOpenTime().compareTo(endTime) < 0)
                .sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public void turnOnDatabaseLogger() {
        iAppDB.getLogger().setLevel(Level.INFO);
    }

    @Override
    public void turnOnTerminalLogger() {
        logger.setLevel(Level.INFO);
    }
}
