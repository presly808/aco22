package week2.company.test;

import week2.company.model.*;

public class TestInheritance {

    public static void main(String[] args) {
        Coder coder = new Coder(1, "Vania", 3000, "+380", "JAVA");
        coder.getName();


        Worker worker = new Coder();

        // Abstraction is-a
        Worker worker2 = new Coder();
        // worker2.code(); - wrong

        // startWork(new Worker());
        startWork(new Coder());


        Tester tester = new Tester(2, "Tester Kolia", 2500, "2", "Auto");

    }

    public static void startWork(Worker worker) {
        if(worker instanceof Coder){ // is-a cheker (hierarchy)
            Coder coderRef  = (Coder) worker;
            coderRef.code();
        } else if(worker.getClass() == Tester.class) { // no hierarchy cheking
            Tester tester = (Tester) worker;
        } else {
            worker.work();
        }
    }

    public static void startWork2(Worker worker) {
        worker.work();
    }
}
