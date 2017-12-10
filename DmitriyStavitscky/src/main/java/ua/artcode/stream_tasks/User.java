package ua.artcode.stream_tasks;

public class User {

    private String name;
    private int age;
    private int id;
    private double salary;
    private String sex;
    private String city;
    private String department;

    public User() {
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

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}