package ua.artcode.market.exclude.exception;

import java.util.function.Supplier;

public class BillNotFoundException extends Exception
        implements Supplier<BillNotFoundException> {
    @Override
    public BillNotFoundException get() {
        return new BillNotFoundException();
    }
}
