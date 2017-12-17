package src.main.java.ua.artcode.market.proxy;

import src.main.java.ua.artcode.market.appDB.IAppDBImp;
import src.main.java.ua.artcode.market.controllers.ITerminal;
import src.main.java.ua.artcode.market.controllers.ITerminalControllerImpl;
import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.models.Statistics;

import java.time.LocalDateTime;
import java.util.List;

public abstract class TerminalControllerProxy implements ITerminal {


    private ITerminalControllerImpl terminal;

    private String action;

    public TerminalControllerProxy(ITerminalControllerImpl terminal) {
        this.terminal = terminal;
    }


    public Salesman addSalesman(String fullName, String login, String password) {
        return terminal.addSalesman(fullName, login, password);
    }



    @Override
    public void signIn(String login, String password) {
        terminal.signIn(login, password);
        action = "Salesman " + terminal.getLoggedSalesman().getFullName() + " logged.";
        rememberActionAndPrint();

    }

    @Override
        public void logOut() {
            terminal.logOut();
            action = "You are logged out at " + LocalDateTime.now();
            rememberActionAndPrint();
        }


    public void createBill() {
        terminal.createBill();
        action = String.format("Salesman %s created a new bill at %s ", terminal.getLoggedSalesman().getFullName(),
                terminal.getCurrentBill().getOpenTime().toString());
        rememberActionAndPrint();

    }

    @Override
    public void closeAndSaveBill() {
      //terminal.closeAndSaveBill(hours, minutes, seconds);
        action = "bill closed. " + terminal.getAllBills().
                get(terminal.getAllBills().size() - 1).
                toString();
        rememberActionAndPrint();

    }

    @Override
    public void addProductToBill(int id) {
        terminal.addProductToBill(id);
    }

    @Override
    public Statistics makeStatistic() {
        return terminal.makeStatistic();   }


   // @Override
   // public List<Bill> filterByTime(List<Bill> bills, Time startTime, Time endTime, Comparator<Bill> comparator) {
   //     return terminal.filterByTime(bills, startTime, endTime, comparator);
   // }
//

    @Override
    public List<Bill> getAllBills() {
        return terminal.getAllBills();
    }

    @Override
        public IAppDBImp getIAppDB() {
            return terminal.getIAppDBImp();
        }

        private void rememberActionAndPrint() {
            terminal.getIAppDBImp().addActionToHistory(action);
            System.out.println(action);
            action = null;
        }

        public int getCountOfBills() {
            return terminal.getAllBills().size();
        }

        public int getCountOfSalesman() {
            return terminal.getSalesmans().size();
        }

        public Salesman getLoggedSalesman() {
            return terminal.getLoggedSalesman();
        }

        public boolean getIsLogged() {
            return terminal.getIsLogged();
        }

        public Bill getCurrentBill() {
            return terminal.getCurrentBill();
        }

        public List<Salesman> getSalesmans() {
            return terminal.getSalesmans();
        }
    }

