package week2.company.model;

public class Tester extends Worker{

    private String type;

    public Tester(int id, String name,
                  double salary, String phone, String type) {
        super(id, name, salary, phone);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void test() {
        System.out.println("Tester test");
    }

    @Override
    public void work() {
        System.out.println("Tester work");
    }
}
