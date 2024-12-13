import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class formlServer {
    public static void main(String[] args) {
        try {
            forml forml = new formlimpl();
            Registry registry = LocateRegistry.createRegistry(1098);
            registry.rebind("forml", forml);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}