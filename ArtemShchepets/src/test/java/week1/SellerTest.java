package week1;

import org.junit.Assert;
import org.junit.Test;
import week1.model.Seller;

public class SellerTest {

    @Test
    public void testData() {
        Seller testSeller = new Seller();

        Assert.assertNotNull(testSeller);
    }
}
