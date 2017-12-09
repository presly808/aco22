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

        if (id != user.id) return false;
        if (age != user.age) return false;
        if (Double.compare(user.salary, salary) != 0) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (sity != null ? !sity.equals(user.sity) : user.sity != null) return false;
        return department != null ? department.equals(user.department) : user.department == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + age;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (sity != null ? sity.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
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