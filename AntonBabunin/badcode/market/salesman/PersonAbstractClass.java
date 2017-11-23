package ua.artcode.market.salesman;

public abstract class PersonAbstractClass {
    private String fullName;

    public PersonAbstractClass(String fullName) {
        this.fullName = fullName;

    }
    public String getFullName() {
        return fullName;
    }

    @Override
    public abstract String toString();
}
