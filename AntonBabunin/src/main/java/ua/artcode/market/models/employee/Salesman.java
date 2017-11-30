package ua.artcode.market.models.employee;

import ua.artcode.market.models.money.Money;

import java.util.List;

public class Salesman extends Employee {

    private int percent;

    public Salesman(String fullName, String login, String password,
                    Money salary) {
        super(fullName, login, password);
        this.setSalary(salary);
        this.percent = 5;
    }

    public Salesman() {
        super();
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
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
