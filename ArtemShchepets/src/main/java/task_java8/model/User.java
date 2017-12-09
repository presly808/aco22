package task_java8.model;

public class User {

    // Input Stream of Users(id,name,sex,salary,city,department)

    private long id;
    private String name;
    private String sex;
    private double salary;
    private String city;
    private int company;
    private int age;

    public User(long id, String name, int age, String sex, double salary, String city, int company) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
        this.city = city;
        this.company = company;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public int getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                ", company=" + company +
                ", age=" + age +
                '}';
    }
}
