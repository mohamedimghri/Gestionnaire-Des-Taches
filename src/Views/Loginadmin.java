
    package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loginadmin extends JFrame{
    private JLabel useremailLabel ,userpasswordLabel;
    private JTextField useremailField  , passwordField;
    private JButton loginButton,resetButton,backButton;
    private adminOrUser adminOrUser;
    
    public Loginadmin(adminOrUser adminOrUser){
        this.adminOrUser = adminOrUser;
        setTitle("Gestionnaire des taches");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255));
        

        JLabel titleLabel = new JLabel("Login Admin");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setBounds(150, 1, 300, 30);;
        titleLabel.setForeground(Color.black); 

        useremailLabel = new JLabel("username:");
        useremailLabel.setBounds(50, 50, 100, 30);
        useremailField = new JTextField();
        useremailField.setBounds(150, 50, 150, 30);

        userpasswordLabel = new JLabel("password:");
        userpasswordLabel.setBounds(50, 100, 100, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 100, 30);
        loginButton.setBackground(new Color(34,193,85));
        loginButton.setForeground(Color.white);

        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 150, 100, 30);
        resetButton.setBackground(new Color(255,127,80));
        resetButton.setForeground(Color.white);
        backButton = new JButton("back");
        backButton.setBounds(1, 1, 70, 30);
        backButton.setBackground(new Color(192,192,192));
        backButton.setForeground(Color.white);

        add(titleLabel);
        add(useremailLabel);
        add(useremailField);
        add(userpasswordLabel);
        add(passwordField);
        add(loginButton);
        add(resetButton);
        add(backButton);
    

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useremailField.setText("");
                passwordField.setText("");
            }
        });
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = useremailField.getText();
                String password =  passwordField.getText();
                    if(username.equals("admin") && password.equals("admin")){
                        JOptionPane.showMessageDialog(new JFrame() ,"Login successfuly");
                        operationAdmin();
                        useremailField.setText("");
                        passwordField.setText("");         
                    }else{
                        JOptionPane.showMessageDialog(new JFrame() ,"username ou password est invalid !!");
                        useremailField.setText("");
                        passwordField.setText("");
                    }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            back();
            }
        });
    }
    public void back(){
        adminOrUser.setVisible(true);
        this.dispose();
    }
    public void operationAdmin(){
        operationAdmin operationAdmin = new operationAdmin(this);
        operationAdmin.setVisible(true);
        this.setVisible(false);
    }
}



