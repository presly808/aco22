package ua.artcode.market.models;

public class Terminal {
    private int id;

    public Terminal(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Terminal terminal = (Terminal) object;

        return id == terminal.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                '}';
    }
}
