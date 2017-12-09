package stream_tasks;

import java.util.Comparator;

/**
 * Created by ENIAC on 03.12.2017.
 */
public class User {

    private int id;
    private int age;
    private double salary;
    private String name;
    private String sex;
    private String sity;
    private String department;


    public User(int id, int age, int salary, String name, String sex, String sity, String department) {
        this.id = id;
        this.age = age;
        this.salary = salary;
        this.name = name;
        this.sex = sex;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSity() {
        return sity;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + id +
                ", age=" + age +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", sity='" + sity + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id == user.id;

    }

}

class ComparatorByAge implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }
}

class ComparatorBySalary implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return (int) (o2.getSalary() - o1.getSalary());
    }
}