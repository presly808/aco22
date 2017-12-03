package taskJava8.model;

public class User {

    // Input Stream of Users(id,name,sex,salary,city,department)

   private long id;
   private String name;
   private String sex;
   private double salary;
   private String city;
   private int company;
   private int age;

    public User() {
    }

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

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public void setCity(String city) {
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.salary, salary) != 0) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        return company != 0 ? company == user.company : user.company == 0;
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
