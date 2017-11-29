package ua.artcode.market.models.money;

public class Money {
    private int moneyWholePart;
    private int moneyFraction;

    public Money(int moneyWholePart, int moneyFraction) {
        this.moneyWholePart = moneyWholePart;
        this.moneyFraction = moneyFraction;
    }

    public int getMoneyWholePart() {
        return moneyWholePart;
    }

    public void setMoneyWholePart(int moneyWholePart) {
        this.moneyWholePart = moneyWholePart;
    }

    public int getMoneyFraction() {
        return moneyFraction;
    }

    public void setMoneyFraction(int moneyFraction) {
        this.moneyFraction = moneyFraction;
    }

    public Money doSum(Money salary) {
        if (salary == null) return this;

        return new Money(this.getMoneyWholePart() + salary.getMoneyWholePart() +
                ((this.getMoneyFraction() + salary.getMoneyFraction()) / 100),
                (this.getMoneyFraction() + salary.getMoneyFraction()) % 100);
    }


//    public static Money doSum(Money mon1, Money mon2) {
//        if (mon1 == null || mon2 == null) return new Money(0,0);
//        else if (mon1 != null && mon2 == null) return mon1;
//        else if (mon1 == null && mon2 != null) return mon2;
//        else return new Money(mon1.getMoneyWholePart() + mon2.getMoneyWholePart() +
//                    ((mon1.getMoneyFraction() + mon2.getMoneyFraction()) / 100),
//                    (mon1.getMoneyFraction() + mon2.getMoneyFraction()) % 100);
//
//    }

    public Money takePercent (int percent) {
        int takedPercent = (this.moneyWholePart + this.moneyFraction) * percent / 100;
        return new Money(takedPercent / 100, takedPercent % 100);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Money salary = (Money) object;

        if (moneyWholePart != salary.moneyWholePart) return false;
        return moneyFraction == salary.moneyFraction;
    }

    @Override
    public int hashCode() {
        int result = moneyWholePart;
        result = 31 * result + moneyFraction;
        return result;
    }

    @Override
    public String toString() {
        return moneyWholePart + "." + moneyFraction;
    }
}
