package ua.artcode.market.interfaces;

import ua.artcode.market.models.Salesman;

import java.io.IOException;
import java.util.List;

public interface ILogging {

    void write (String messege) throws IOException;

    List<Salesman> getAllSalesmans();
    Salesman createSalesman(String fullName, String login, String password) throws IOException;
    Salesman login(String login, String password) throws IOException;
    Salesman logout(Salesman salesman) throws IOException;
    Salesman findSalesmanByLogin(String login);
}
