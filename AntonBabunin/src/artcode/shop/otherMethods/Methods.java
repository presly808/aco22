package artcode.shop.otherMethods;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Methods {
    public static double round(double d, int pricise) {
        return new BigDecimal(d).setScale(pricise, RoundingMode.HALF_UP).doubleValue();
    }
}
