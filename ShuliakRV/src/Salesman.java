public class Salesman {

    private static int seqId;
    private int id;
    private String fullname;
    private String login;
    private String password;

    public Salesman(String fullname, String login, String password) {
        seqId++;
        id = seqId;
        this.fullname = fullname;
        this.login = login;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}
