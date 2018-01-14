package week1.utils;

public class VerificationToken {

    public static String loginToken(){
     return  Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
