package week2.company.model;


// is-a
public class Coder extends Worker {
    // get methods and fields (public, protected)

    private String programmingLenguage;

    public Coder() {
        // super(-1, "Coder", 3000, "undefined");
    }

    public Coder(int id, String name, double salary,
                 String phone, String programmingLenguage) {
        this.programmingLenguage = programmingLenguage;
    }

    public String getProgrammingLenguage() {
        return programmingLenguage;
    }

    public void setProgrammingLenguage(String programmingLenguage) {
        this.programmingLenguage = programmingLenguage;
    }

    public void code() {
        System.out.println("coder code");
    }

    @Override
    public void work() {
        System.out.println("Coder work");
    }

    public String convert(){
        return String.format("id %d, name %s", getId(), getName());
    }
}
