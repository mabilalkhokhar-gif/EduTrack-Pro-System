import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextField userField;
    JPasswordField passField;

    public LoginFrame() {

        setTitle("EduTrack Pro - Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("ADMIN LOGIN");
        title.setBounds(140, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);

        userField = new JTextField();
        userField.setBounds(150, 80, 180, 25);

        passField = new JPasswordField();
        passField.setBounds(150, 120, 180, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 170, 100, 30);

        loginBtn.addActionListener(e -> login());

        add(title);
        add(userLabel);
        add(passLabel);
        add(userField);
        add(passField);
        add(loginBtn);

        setVisible(true);
    }

    void login() {

        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.equals("UCP") && pass.equals("ucp123")) {

            new DashboardFrame();

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Login");
        }
    }
}