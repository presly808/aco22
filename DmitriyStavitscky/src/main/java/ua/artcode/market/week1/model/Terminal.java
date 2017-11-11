package main.java.ua.artcode.market.week1.model;


public class Terminal {

    public static final int MAX_COUNT_OF_BILLS = 10;
    public static final int MAX_COUNT_OF_SALESMANS = 10;


    private Bill[] bills = new Bill[MAX_COUNT_OF_BILLS];
    private int countOfBills;

    private Salesman[] salesmans = new Salesman[MAX_COUNT_OF_SALESMANS];
    private int countOfSalesman;

    private Salesman loggedSalesman;
    private boolean logged;

    public void addSalesman(String fullName, String login, int pass) {

        if (countOfSalesman == MAX_COUNT_OF_SALESMANS) {
            System.out.println("Sorry, you've reached the maximum number of checks");
        } else if (fullName.isEmpty() || login.isEmpty() || pass <= 0) {
            System.out.println("wrong data");
        } else {
            salesmans[countOfSalesman++] = new Salesman(fullName, login, pass);
        }
    }

    public void signIn(boolean isLogin, String loginOrName, int password) {
        if (findSalesman(loginOrName, isLogin) == null ||
                findSalesman(loginOrName, isLogin).getPass() != password) {
            System.out.println("wrong data");
        } else {
            this.loggedSalesman = findSalesman(loginOrName, isLogin);
            logged = true;
        }
    }

    public void createBill(int id) {

        if (!logged) {
            System.out.println("please log in");

        } else if (countOfBills == MAX_COUNT_OF_BILLS) {
            System.out.println("Sorry, you've reached the maximum number of checks");

        } else {
            bills[countOfBills] = new Bill(loggedSalesman, id);
        }
    }

    public void closeAndSaveBill(int hours, int minutes, int seconds) {
        if (!logged) {
            System.out.println("please log in");
        } else if (bills[countOfBills].getProducts() == null) {
            System.out.println("you did not make a single sale");
        } else {
            bills[countOfBills++].closeBill(hours, minutes, seconds);
        }
    }

    public void addProduct(String name, int id, double price) {
        if (logged) {
            bills[countOfBills].setProducts(name, id, price);
        } else {
            System.out.println("please log in");
        }
    }

    public Bill findBillById(int id) {
        if (id == 0) return null;

        for (int i = 0; i < countOfBills; i++) {
            if (bills[i].getId() == id) {
                return bills[i];
            }
        }

        return null;
    }

    public Salesman findSalesman(String loginOrName, boolean isLogin) {
        if (loginOrName == null || loginOrName.isEmpty()) return null;

        if (isLogin) {
            for (int i = 0; i < countOfSalesman; i++) {
                if (salesmans[i].getLogin().equals(loginOrName)) {
                    return salesmans[i];
                }
            }
        } else {
            for (int i = 0; i < countOfSalesman; i++) {
                if (salesmans[i].getFullName().equals(loginOrName)) {
                    return salesmans[i];
                }
            }

        }

        return null;
    }

    public Salesman getTopNofSalesMan() {
        int topSalemanId = 0;

        if (countOfSalesman == 0) return null;

        double max = salesmans[0].getSumOfAllSales();
        for (int i = 1; i < countOfSalesman; i++) {
            if (salesmans[i].getSumOfAllSales() > max) {
                max = salesmans[i].getSumOfAllSales();
                topSalemanId = i;
            }
        }

        return salesmans[topSalemanId];
    }

    public void doSomeStatisticStuff() {
        if (countOfBills == 0) {
            System.out.println("count of bills = 0");
        } else {

            double maxAmount = bills[0].getAmountPrice();
            int billIdWithMaxAmount = 0;

            double minAmount = bills[0].getAmountPrice();
            int billIdWithMinAmount = 0;

            double averageAmountInOneChek = bills[0].getAmountPrice();
            double sumOfAllSalles = bills[0].getAmountPrice();

            for (int i = 1; i < countOfBills; i++) {
                if (bills[i].getAmountPrice() > maxAmount) {
                    maxAmount = bills[i].getAmountPrice();
                    billIdWithMaxAmount = i;
                }

                if (bills[i].getAmountPrice() < minAmount) {
                    minAmount = bills[i].getAmountPrice();
                    billIdWithMinAmount = i;
                }

                averageAmountInOneChek += bills[i].getAmountPrice();
                sumOfAllSalles += bills[i].getAmountPrice();
            }

            averageAmountInOneChek = averageAmountInOneChek / countOfBills;

            System.out.printf("Max amount: %.2f, saleman: %s \n" +
                            "Min amount: %.2f, saleman: %s \n" +
                            "Average amount in one chek: %.2f \n" +
                            "Sum of all salles: %.2f",
                    maxAmount, salesmans[billIdWithMaxAmount].getFullName(),
                    minAmount, salesmans[billIdWithMinAmount].getFullName(),
                    averageAmountInOneChek,
                    sumOfAllSalles);
        }
    }

    public Bill[] getBills() {
        return bills;
    }

    public int getCountOfBills() {
        return countOfBills;
    }

    public Salesman[] getSalesmans() {
        return salesmans;
    }

    public int getCountOfSalesman() {
        return countOfSalesman;
    }

    public Salesman getLoggedSalesman() {
        return loggedSalesman;
    }

    public boolean getIsLogged() {
        return logged;
    }

    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public void setCountOfBills(int countOfBills) {
        this.countOfBills = countOfBills;
    }

    public void setSalesmans(Salesman[] salesmans) {
        this.salesmans = salesmans;
    }

    public void setCountOfSalesman(int countOfSalesman) {
        this.countOfSalesman = countOfSalesman;
    }

    public void setLoggedSalesman(Salesman loggedSalesman) {
        this.loggedSalesman = loggedSalesman;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
