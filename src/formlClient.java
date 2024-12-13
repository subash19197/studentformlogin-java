
import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class formlClient {
    private Frame frame;
    private TextField usernameField;
    private TextField passwordField;
    private Label messageLabel;

    public formlClient() {
        frame = new Frame("Login Form");
        usernameField = new TextField(20);
        passwordField = new TextField(20);
       
        Button loginButton = new Button("Login");
        messageLabel = new Label("");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = usernameField.getText();
                String prd = passwordField.getText();
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 1098);
                    forml forml = (forml) registry.lookup("forml");
                    boolean valid = forml.stdData(uname, prd);
                    messageLabel.setText(valid ? "Login Successful!" : "Invalid Credentials!");
                } catch (Exception ex) {
                    messageLabel.setText("Error: " + ex.getMessage());
                }
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(new Label("Username:"));
        frame.add(usernameField);
        frame.add(new Label("Password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(messageLabel);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new formlClient();
    }
}