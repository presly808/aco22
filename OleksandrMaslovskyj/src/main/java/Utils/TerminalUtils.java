package main.java.Utils;


import java.util.UUID;

public class TerminalUtils {

    public static long longIdGenerator(){
        return UUID.randomUUID().getMostSignificantBits();
    }

}
