
package ua.artcode.market.models;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;

import java.io.IOException;

public class Terminal {
    private int id;

    public Terminal(int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Terminal terminal = (Terminal) object;

        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                '}';
    }

    public Employee login(String login, String password) throws IOException {
        return null;
    }
    public Employee logout(Salesman salesman) throws IOException {
        return null;
    }
}

