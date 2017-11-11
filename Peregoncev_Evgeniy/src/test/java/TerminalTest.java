import org.junit.Assert;
import org.junit.Test;


/**
 * Created by ENIAC on 11.11.2017.
 */
public class TerminalTest {

    @Test
    public void testShowInfo() {
        Product product1 = new Product("Макарошки", 40, "#0001");
        String actual = product1.printFullInfo();
        String expected = "name Макарошки price 40,000000 barcode #0001 barcode";
        Assert.assertEquals(actual, expected);
    }

}
