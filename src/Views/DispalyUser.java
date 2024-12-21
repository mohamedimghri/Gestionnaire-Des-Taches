package Views;
import javax.swing.*;

import Controllers.displayUserController;
import Models.Utilisateur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DispalyUser extends JFrame {
private displayUserController displayUserController = new displayUserController();
private loginUser loginUser;
public DispalyUser(loginUser loginUser){
     this.loginUser = loginUser;
     Utilisateur user = displayUserController.dispalyUser();
     String userName=user.getName();
     String userTask=user.getTachetitle();
     String deadline=user.getDeadline();
     String description = displayUserController.getDescription();

     setTitle("Gestionnaire des taches");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setSize(400, 300);
     setLocationRelativeTo(null);

     setLayout(new BorderLayout());

     JPanel panel = new JPanel();
     panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
     panel.setBackground(new Color(230, 240, 255));
     panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

     JLabel titleLabel = new JLabel("Welcome !!");
     titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 18));
     titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
     titleLabel.setForeground(Color.black); 

     JLabel nameLabel = new JLabel("Name: " + userName);
     nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
     nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

     JLabel tachLabel = new JLabel("Tache: " + userTask);  
     tachLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     tachLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

     JLabel descriptionlabel = new JLabel("description: " + description);  
     descriptionlabel.setFont(new Font("Arial", Font.PLAIN, 14));
     descriptionlabel.setAlignmentX(Component.CENTER_ALIGNMENT);

     JLabel deadlineLabel = new JLabel("Deadline: " + deadline);
     deadlineLabel.setFont(new Font("Arial", Font.ITALIC, 12));
     deadlineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

     panel.add(titleLabel);
     panel.add(Box.createVerticalStrut(20)); 
     panel.add(nameLabel);
     panel.add(Box.createVerticalStrut(10)); 
     panel.add(tachLabel);
     panel.add(Box.createVerticalStrut(10)); 
     panel.add(descriptionlabel);
     panel.add(Box.createVerticalStrut(10)); 
     panel.add(deadlineLabel);

     JPanel buttonpanel = new JPanel();
     JButton logoutButton = new JButton("Log-Out");
     logoutButton.setBackground(new Color(220,20,60));
     logoutButton.setForeground(Color.white);
     JButton notificaButton = new JButton("Deadline");
     notificaButton.setBackground(new Color(255,197,103));
     JButton terminéButton = new JButton("terminée");
     terminéButton.setBackground(new Color(75,112,102));
     terminéButton.setForeground(Color.white);
     
     buttonpanel.add(logoutButton);
     buttonpanel.add(notificaButton);
     buttonpanel.add(terminéButton);

     add(buttonpanel, BorderLayout.SOUTH);
     add(panel, BorderLayout.CENTER);


     logoutButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(new JFrame() ,"Log-Out successfuly");
          logout();
          }
     });
     notificaButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(new JFrame(), "Deadline: " + deadline, "Deadline Notification", JOptionPane.INFORMATION_MESSAGE);
          }
     });
     terminéButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
          displayUserController.terminerTache();
          JOptionPane.showMessageDialog(new JFrame(), "votre tache terminéé", "Tache terminée", JOptionPane.INFORMATION_MESSAGE);
          }
     });
}
public void logout(){
     loginUser.setVisible(true);
     this.dispose(); 
}
}