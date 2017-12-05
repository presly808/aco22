package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ILogging;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
//import ua.artcode.market.models.money.Salary;
import ua.artcode.market.models.money.Money;
import ua.artcode.market.utils.Generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ILoggingImpl implements ILogging {

    private static volatile ILogging instance;

    private List<Employee> salesmenList;

    private ILoggingImpl() throws IOException {
        this.salesmenList = Generator.generateSalesmanList(0);
        for (Employee salesman : salesmenList){
            write(String.format("Fullname: %s, Login: %s, Password: %s, \r\n",
                    salesman.getFullName(), salesman.getLogin(),
                    salesman.getPassword()));
        }
    }

    public static ILogging getInstance() throws IOException {
        if (instance == null) {
            synchronized (ILoggingImpl.class) {
                if (instance == null) {
                    instance = new ILoggingImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void write(String messege)
            throws IOException {
        File file = new File("log.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(messege);
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public List<Employee> getAllSalesmans() {
        return salesmenList;
    }

    @Override
    public Employee createSalesman(String fullName, String login, String password) {
        Employee salesman = null;
        Employee seller = findSalesmanByLogin(login);
        if (seller == null) {
            salesman = new Salesman(fullName, login, password, new Money(123,12));
            salesmenList.add(salesman);
        }
        return salesman;
    }

    @Override
    public Employee login(String login, String password) throws IOException {
        Employee seller = findSalesmanByLogin(login);
        if (seller == null) {
            return null;
        }
        if (!password.equals(seller.getPassword())) {
            return null;
        }
        seller.setIsConnected(true);
        return seller;
    }

    @Override
    public Salesman logout(Salesman salesman) {
        salesman.setIsConnected(false);
        return salesman;
    }

    @Override
    public Employee findSalesmanByLogin(String login) {
        for (Employee salesman : salesmenList) {
            if (login.equals(salesman.getLogin())) {
                return salesman;
            }
        }
        return null;
    }
}
