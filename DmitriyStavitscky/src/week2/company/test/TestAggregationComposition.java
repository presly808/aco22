package week2.company.test;

import week2.company.model.Director;
import week2.company.model.Manager;

public class TestAggregationComposition {


    public static void main(String[] args) {

        // lifecycle object
        // aggregation
        Manager manager = new Manager();
        Director director = new Director();
        director.setRightHand(manager);

        director = null;

        // Composition

        Director director1 = new Director();
        director1.setRightHand(new Manager());

        director1 = null;

    }
}
