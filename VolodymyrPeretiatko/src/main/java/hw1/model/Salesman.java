package hw1.model;

public class Salesman extends DBItem{

    private String fullName;
    private String name;
    private String pass;

    public Salesman(String fullName, String name, String pass) {
        this.fullName = fullName;
        this.name = name;
        this.pass = pass;
    }

    public Salesman(int id) {
        super.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salesman)) return false;

        Salesman salesman = (Salesman) o;

        if (!fullName.equals(salesman.fullName)) return false;
        if (!name.equals(salesman.name)) return false;
        return pass.equals(salesman.pass);

    }

    @Override
    public String toString() {
        return "Salesman{" +
                "fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + pass.hashCode();
        return result;
    }
}
