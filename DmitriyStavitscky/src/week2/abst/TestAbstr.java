package week2.abst;

public class TestAbstr {

    public static void main(String[] args) {
        startLoggin(new ConsoleLogger());

        startLoggin(new FileLogger() {
        });
       /* BaseLogger someLogger = new FileLogger();
        *//*FileLogger fileLogger = new FileLogger();
        fileLogger.start();*/
    }

    public static void startLoggin(BaseLogger loger){
        loger.start();

    }
}
