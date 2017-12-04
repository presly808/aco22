package ua.artcode.stream_tasks;

import java.util.Comparator;

public class User {

    // Input Stream of Users(id,name,sex,salary,city,department)

    private String name;
    private int age;
    private int id;
    private double salary;
    private String sex;
    private String city;
    private String department;

    public User() {
    }

    public User(String name, double salary, String city) {
        this.name = name;
        this.salary = salary;
        this.city = city;
    }

    public User(String name, int age, int id, double salary, String sex, String city, String department) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.salary = salary;
        this.sex = sex;
        this.city = city;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return age == user.age &&
                id == user.id &&
                Double.compare(user.salary, salary) == 0 &&
                name.equals(user.name) &&
                sex.equals(user.sex) &&
                city.equals(user.city) &&
                department.equals(user.department);
    }

    @Override
    public String toString() {
        return "User{\n" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", salary=" + salary +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", department='" + department + '\'' +
                "}\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) { this.salary = salary; }
}