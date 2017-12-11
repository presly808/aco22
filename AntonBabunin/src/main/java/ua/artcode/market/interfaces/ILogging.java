package ua.artcode.market.interfaces;

import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;

import java.io.IOException;
import java.util.List;

public interface ILogging {

    void write (String messege) throws IOException;

    List<Employee> getAllSalesmans();
    Employee createSalesman(String fullName, String login, String password) throws IOException;
    Employee login(String login, String password) throws IOException;
    Salesman logout(Salesman salesman) throws IOException;
    Employee findSalesmanByLogin(String login);
}
