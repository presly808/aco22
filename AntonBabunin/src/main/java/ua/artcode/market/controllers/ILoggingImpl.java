package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.ILogging;
import ua.artcode.market.models.Salesman;
import ua.artcode.market.utils.Generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ILoggingImpl implements ILogging {

    private static volatile ILogging instance;

    private List<Salesman> salesmenList;

    private ILoggingImpl(){
        this.salesmenList = Generator.generateSalesmanList(10);
    }

    public static ILogging getGetInstance() {
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
        File file = new File("C:\\Projects\\Java\\Gesserok\\aco22\\logs.txt");
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(messege);
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public List<Salesman> getAllSalesmans() {
        return salesmenList;
    }

    @Override
    public Salesman createSalesman(String fullName, String login, String password) {
        Salesman salesman = null;
        Salesman seller = findSalesmanByLogin(login);
        if (seller == null) {
            salesman = new Salesman(fullName, login, password);
            salesmenList.add(salesman);
        }
        return salesman;
    }

    @Override
    public Salesman login(String login, String password) throws IOException {
        Salesman seller = findSalesmanByLogin(login);
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
    public Salesman findSalesmanByLogin(String login) {
        for (Salesman salesman : salesmenList) {
            if (login.equals(salesman.getLogin())) {
                return salesman;
            }
        }
        return null;
    }
}
