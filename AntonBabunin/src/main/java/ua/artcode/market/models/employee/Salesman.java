package ua.artcode.market.models.employee;

import ua.artcode.market.models.money.Money;
//import ua.artcode.market.models.money.Salary;

import java.util.List;

public class Salesman extends Employee {

    public Salesman(String fullName, String login, String password,
                    Money salary) {
        super(fullName, login, password);
        this.setSalary(salary);
    }

    public Salesman() {
        super();
    }

    @Override
    public void setSubordinateList(List<Employee> subordinateList) {

    }

    @Override
    public List getSubordinateList() {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Salesman worker = (Salesman) object;

        return this.getLogin() !=
                null ? this.getLogin().equals(worker.getLogin()) :
                worker.getLogin() == null;
    }

}
