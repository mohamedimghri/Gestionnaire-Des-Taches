package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class operationAdmin extends JFrame {
    JButton tacheButton , userButton , equipeButton,logoutButton ;
    private Loginadmin loginadmin ;
    public operationAdmin(Loginadmin loginadmin){
        this.loginadmin = loginadmin ;
        setTitle("Gestionnaire des taches");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel titleLabel = new JLabel("choisie que vous avez faire un trairement");
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 19));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(50,10,400,30);
        

        tacheButton = new JButton("Tache");
        tacheButton.setBounds(40, 150, 100, 30);
        tacheButton.setBackground(new Color(52,58,64));
        tacheButton.setForeground(Color.white);
        userButton= new JButton("User");
        userButton.setBounds(200, 150, 100, 30);
        userButton.setBackground(new Color(52,58,64));
        userButton.setForeground(Color.white);
        equipeButton= new JButton("Equipe");
        equipeButton.setBounds(350, 150, 100, 30);
        equipeButton.setBackground(new Color(52,58,64));
        equipeButton.setForeground(Color.white);

        logoutButton = new JButton("Log-out");
        logoutButton.setBounds(200, 200, 100, 30);
        logoutButton.setBackground(new Color(220,20,60));
        logoutButton.setForeground(Color.white);

        add(tacheButton);
        add(userButton);
        add(equipeButton);
        add(titleLabel);
        add(logoutButton);

        tacheButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttontach();
            }
        });
        userButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonuser();
            }
        });
        equipeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonteam();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(new JFrame() ,"Log-Out successfuly");
            logout();
            }
        });
    }
    public void logout(){
        loginadmin.setVisible(true);
        this.dispose();
    }
    public void buttontach(){
        TacheView  tacheView = new TacheView(this);
        tacheView.setVisible(true);
        this.setVisible(false);
    }
    public void buttonuser(){
        UserView userView = new UserView(this);
                userView.setVisible(true);
                this.setVisible(false);
    }
    public void buttonteam(){
        TeamView teamView = new TeamView(this);
            teamView.setVisible(true);
            this.setVisible(false);
    }
    }

