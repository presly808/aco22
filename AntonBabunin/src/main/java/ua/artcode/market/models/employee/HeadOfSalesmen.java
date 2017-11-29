package ua.artcode.market.models.employee;

import ua.artcode.market.models.money.Money;
//import ua.artcode.market.models.money.Salary;
import ua.artcode.market.utils.Generator;

import java.util.List;

public class HeadOfSalesmen extends Salesman {

    private List<Employee> subordinateList;

    public HeadOfSalesmen(String fullName, String login, String password,
                          Money salary) {
        super(fullName, login, password, salary);
        this.subordinateList = Generator.CreateSubordinateList();
    }

    public List<Employee> getSubordinateList() {
        return subordinateList;
    }

    public void setSubordinateList(List<Employee> subordinateList) {
        this.subordinateList = subordinateList;
    }
}
