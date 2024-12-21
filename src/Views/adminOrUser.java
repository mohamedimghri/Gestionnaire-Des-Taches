package Views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class adminOrUser extends JFrame {
    JButton admiButton , userButton ;
    public adminOrUser(){
        
        setTitle("Gestionnaire-des-Tach");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel titleLabel = new JLabel("Gestionnaire-des-Tache");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 19));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(130,10,400,30);
        

        admiButton = new JButton("Admin");
        admiButton.setBounds(100, 150, 100, 30);
        admiButton.setBackground(new Color(65,105,255));
        admiButton.setForeground(Color.white);
        userButton= new JButton("User");
        userButton.setBounds(300, 150, 100, 30);
        userButton.setBackground(new Color(0,150,136));
        userButton.setForeground(Color.white);

        add(admiButton);
        add(userButton);
        add(titleLabel);
        admiButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                adminButton();
            }
        });
        userButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {  
            userButton();
            }
        });
    }
    
    public void adminButton(){
        Loginadmin loginadmin = new Loginadmin(this);
            loginadmin.setVisible(true);
            this.setVisible(false);
    }
    public void userButton(){
        loginUser loginUser = new loginUser(this);
        loginUser.setVisible(true);
        this.setVisible(false);
    }
}
