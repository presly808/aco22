package src.main.java.ua.artcode.market.controllers;

import src.main.java.ua.artcode.market.Utils.TerminalUtils;
import src.main.java.ua.artcode.market.appDB.IAppDB;
import src.main.java.ua.artcode.market.appDB.IAppDBImp;
import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.models.Statistics;
import src.main.java.ua.artcode.market.controllers.ITerminal;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.sun.xml.internal.xsom.impl.UName.comparator;

public abstract class ITerminalControllerImpl implements ITerminal {

    private IAppDBImp iAppDB;
    private Bill newBill;
    private Salesman loggedSalesman;
    private boolean logged;

    public ITerminalControllerImpl(IAppDBImp iAppDB) {
        this.iAppDB = iAppDB;
    }


    @Override
    public void addSalesman(String fullName, String login, String password) {
        if (fullName.isEmpty() || login.isEmpty() || password.isEmpty()) {
            System.out.println("wrong data");
            return null;

        } else {
            iAppDB.getSalesmans().add(new Salesman(fullName, login, password, iAppDB.genId()));
            return iAppDB.findSalesmanByLoginOrName(login);
        }
    }

    @Override
    public void signIn(String login, String password) {

        if (logged) {
            System.out.println("You already logged");

        } else if (iAppDB.findSalesmanByLoginOrName(loginOrName) == null ||
                iAppDB.findSalesmanByLoginOrName(loginOrName).getPass().equals(password)) {
            System.out.println("wrong data");

        } else {
            this.loggedSalesman = iAppDB.findSalesmanByLoginOrName(loginOrName);
            logged = true;

        }
    }

        @Override
        public void logOut () {

            logged = false;
            loggedSalesman = null;

        }

        @Override
        public void createBill () {

            if (!logged) {
                System.out.println("please log in");

            } else {
                currentBill = new Bill(loggedSalesman, iAppDB.genId());
            }

        }

        @Override
        public void closeAndSaveBill () {

            if (currentBill.getProducts().size() == 0) {
                System.out.println("you did not make a single sale, the bill wil be deleted");

            } else {
                currentBill.calculateAmountPrice();
                currentBill.setCloseTime(new Time());
                iAppDB.getBills().add(currentBill);
                currentBill.getSalesman().addSum(currentBill.getAmountPrice());
                currentBill = null;

            }
        }

            @Override
            public void addProductToBill () {

                if (logged && iAppDB.findProductById(id) != null) {
                    currentBill.getProducts().add(iAppDB.findProductById(id));
                    System.out.println("Added product: " + iAppDB.findProductById(id).toString());

                } else {
                    System.out.println("please log in");
                }

            }

            @Override
            public Statistics makeStatistic () {
                if (iAppDB.getBills().size() == 0) {
                    System.out.println("count of bills = 0");
                    return null;

                } else {


                    //search average amount and sum of all sales
                    double sumOfAllSalles = iAppDB.getBills().get(0).getAmountPrice();

                    for (int i = 1; i < iAppDB.getBills().size(); i++) {
                        sumOfAllSalles += iAppDB.getBills().get(i).getAmountPrice();
                    }

                    double averageAmountInOneChek = sumOfAllSalles / iAppDB.getBills().size();

                    // search bil with max and min amount
                    Bill billWithMaxAmount = TerminalUtils.billWithMaxAmount(iAppDB.getBills());
                    Bill billWithMinAmount = TerminalUtils.billWithMinAmount(iAppDB.getBills());

                    return new Statistics(billWithMaxAmount.getAmountPrice(),
                            billWithMaxAmount.getSalesMan(),
                            billWithMinAmount.getAmountPrice(),
                            billWithMinAmount.getSalesMan(),
                            averageAmountInOneChek,
                            amountOfAllSales);

                }
            }

            @Override
            public List<Bill> filterByTime (List < Bill > bills, Time startTime, Time
            endTime, Comparator < Bill > comparator){
                List<Bill> billsFilter = new ArrayList<>();

                for (Bill bill : bills) {

                    if (bill.getCloseTime().compareTo(startTime) > 0 &&
                            bill.getCloseTime().compareTo(endTime) < 0) {
                        billsFilter.add(bill);
                    }
                }

                iAppDB.getBills().sort(comparator);
                return billsFilter;
            }


            @Override
            public List<Bill> getAllBills () {
                return iAppDB.getBills();
            }

            @Override

            public IAppDB getiAppDB () {
                return iAppDB;
            }

        public Bill getNewBill () {
            return newBill;
        }

        public Salesman getLoggedSalesman () {
            return loggedSalesman;
        }

        public boolean isLogged () {
            return logged;
        }


    }
