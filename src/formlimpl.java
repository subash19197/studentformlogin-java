import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class formlimpl extends UnicastRemoteObject implements forml {
    
    public formlimpl() throws RemoteException {
        super();
    }

    @Override
    public boolean stdData(String uname, String prd) throws RemoteException {
        String url = "jdbc:mysql://localhost:3306/form1"; 
        String dbUsername = "root"; 
        String dbPassword = "9342864391";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM form1 WHERE un = ? AND pd = ?")) {
            stmt.setString(1, uname);
            stmt.setString(2, prd);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // returns true if user exists
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}