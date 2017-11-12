package week2.company.model;

public class Manager {
    
    
    private Coder[] coders;
    private Tester[] testers;
    private Manager[] managers;
    
    public void addCoder(){}
    public void addTester(){}
    public void addManager(){}
    
    private Worker[] workers;
    
    public void addWorker(Worker worker){
        // add to the array
    }
    
    public void doTeamWork(){
        for (int i = 0; i < workers.length; i++) {
            workers[i].work();
        }
    }
}
