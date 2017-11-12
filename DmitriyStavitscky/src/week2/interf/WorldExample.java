package week2.interf;

public class WorldExample {

    public static void main(String[] args) {
        Employee employee = new Employee();
        Student student = new Student();
        SuperPerson superPerson = new SuperPerson();



        Company company = new Company();

        University university = new University();

        company.visit(employee);
        company.visit(superPerson);
        university.visit(student);
        university.visit(superPerson);
    }
}

interface IEmployee {
    void work();

}

class Company {

    void visit(Employee empl){
        empl.work();
    }

    public void visit(SuperPerson superPerson) {
    }
}


class University{

    void visit(IStudent student){
        student.study();
    }

}


class SuperPerson implements IEmployee, IStudent{

    @Override
    public void work() {
        System.out.println("SP work");
    }

    @Override
    public void study() {
        System.out.println("SP study");
    }
}


interface IStudent {
    void study();
}

class Student implements IStudent{

    public void study(){
        System.out.println("Student study");
    }
}

class Employee {

    public  void work(){
        System.out.println("work");
    }

}
