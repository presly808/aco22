import static org.junit.Assert.*;

public class TerminalTest {
    @org.junit.Test
    public void login() throws Exception {
        Product grecha = new Product();
        Bill bill = new Bill();
        Terminal ter = new Terminal();

        boolean status = ter.sales[0].status;
        assertFalse(status);
    }

}