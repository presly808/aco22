package week3.model;

public class Salesman {
    private int id;
    private String login;
    private String password;
    private String fullName;

    public Salesman(int id, String login, String password, String fullName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
    }

    public Salesman() {
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salesman salesman = (Salesman) o;

        return fullName != null ? fullName.equals(salesman.fullName) : salesman.fullName == null;
    }

    @Override
    public int hashCode() {
        return fullName != null ? fullName.hashCode() : 0;
    }
}
