package week2.company.model;

public abstract class Worker {

    private int id;
    private String name;
    private double salary;
    private String phone;

    public Worker() {
    }

    public Worker(int id, String name, double salary, String phone) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public abstract void work(); // no body, must be overrode, within abstract class;

    public Worker getOwnSelf(){
        return this;
    }
}
