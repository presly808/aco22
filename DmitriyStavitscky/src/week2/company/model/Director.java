package week2.company.model;

public class Director extends Worker{

    private Manager rightHand;

    public Manager getRightHand() {
        return rightHand;
    }

    public void setRightHand(Manager rightHand) {
        this.rightHand = rightHand;
    }

    @Override
    public void work(){
        System.out.println("Director work method");
    }

    // chek if is real override
    @Override
    public Director getOwnSelf() { // is-a
        return this;
    }
}
