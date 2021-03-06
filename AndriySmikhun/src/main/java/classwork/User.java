package classwork;

public class User {
    private int id;
    private String name;
    private String sex;
    private int age;
    private int salary;
    private String sity;
    private String department;

    public User(int id, String name, String sex, int age, int salary, String sity, String department) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
        this.sity = sity;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getSity() {
        return sity;
    }

    public String getDepartment() {
        return department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}