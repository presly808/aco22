package src.main.java.ua.artcode.market.controllers;

import src.main.java.ua.artcode.market.Utils.TerminalUtils;
import src.main.java.ua.artcode.market.appDB.IAppDB;
import src.main.java.ua.artcode.market.appDB.IAppDBImp;
import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.models.Statistics;

import java.util.List;


public abstract class ITerminalControllerImpl implements ITerminal {

    private IAppDBImp iAppDB;
    private Bill currentBill;
    private Salesman loggedSalesman;
    private boolean logged;

    public ITerminalControllerImpl(IAppDBImp iAppDB) {
        this.iAppDB = iAppDB;
    }


    @Override
    public Salesman addSalesman(String fullName, String login, String password) {
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

        } else if (iAppDB.findSalesmanByLoginOrName(login) == null ||
                iAppDB.findSalesmanByLoginOrName(login).getPass().equals(password)) {
            System.out.println("wrong data");

        } else {
            this.loggedSalesman = iAppDB.findSalesmanByLoginOrName(login);
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
                iAppDB.getAllBills().add(currentBill);
                currentBill.getSalesman().addSum(currentBill.getAmountPrice());
                currentBill = null;
            }
        }

            @Override
            public void addProductToBill (int id) {

                if (logged && iAppDB.findProductById(id) != null) {
                    currentBill.getProducts().add(iAppDB.findProductById(id));
                    System.out.println("Added product: " + iAppDB.findProductById(id).toString());

                } else {
                    System.out.println("please log in");
                }
            }

            @Override
            public Statistics makeStatistic () {
                if (iAppDB.getAllBills().size() == 0) {
                    System.out.println("count of bills = 0");
                    return null;

                } else {


                    //search average amount and sum of all sales
                    double sumOfAllSales = iAppDB.getAllBills().get(0).getAmountPrice();

                    for (int i = 1; i < iAppDB.getAllBills().size(); i++) {
                        sumOfAllSales += iAppDB.getAllBills().get(i).getAmountPrice();
                    }

                    double averageAmountInOneChek = sumOfAllSales / iAppDB.getAllBills().size();

                    // search bil with max and min amount
                    Bill billWithMaxAmount = TerminalUtils.billWithMaxAmount(iAppDB.getAllBills());
                    Bill billWithMinAmount = TerminalUtils.billWithMinAmount(iAppDB.getAllBills());

                    //поменять последовательность
                    return new Statistics(billWithMaxAmount.getSalesMan(),
                            billWithMinAmount.getAmountPrice(),
                            billWithMinAmount.getSalesMan(),
                            billWithMinAmount.getAmountPrice(),
                            averageAmountInOneChek,
                            sumOfAllSales);

                }
            }

           /* @Override
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
*/

            @Override
            public List<Bill> getAllBills () {
                return iAppDB.getAllBills();
            }

            public IAppDB getIAppDB () {
                return iAppDB;
            }
            public Bill getCurrentBill () {
            return currentBill;
        }
            public Salesman getLoggedSalesman () {
            return loggedSalesman;
        }
            public boolean isLogged () {
            return logged;
        }


    }
