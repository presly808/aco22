package Utils;


import java.util.UUID;

public class Utils {

    public static long longIdGenerator(){
        return UUID.randomUUID().getMostSignificantBits();
    }

}
