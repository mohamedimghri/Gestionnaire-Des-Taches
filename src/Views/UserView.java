package Views ;
import Controllers.UsersController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame {

    private JTextField nameName , emailName , passwordName ,deadlineField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> tache ;
    UsersController usersController = new UsersController(); 
    private operationAdmin operationAdmin ;

    public UserView(operationAdmin operationAdmin) {
        this.operationAdmin = operationAdmin;
        setTitle("Gestionnaire d'Utilisateur");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel titleLabel = new JLabel("User", JLabel.CENTER);
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.black);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Tache"));
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));
        formPanel.setPreferredSize(new Dimension(500,100));
        formPanel.setBackground(new Color(230, 240, 255));

        formPanel.add(new JLabel("Name:"));
        nameName = new JTextField(5);
        formPanel.add(nameName);

        formPanel.add(new JLabel("Email:"));
        emailName = new JTextField(5);
        formPanel.add(emailName);

        formPanel.add(new JLabel("Password:"));
        passwordName = new JTextField(20);
        formPanel.add(passwordName);
        add(formPanel, BorderLayout.WEST);

        
        
        formPanel.add(new JLabel("tache Title:"));
        tache = new JComboBox<>();
        usersController.definirTache(tache);
        formPanel.add(tache);
        

        formPanel.add(new JLabel("Deadline:"));
        deadlineField = new JTextField(5);
        formPanel.add(deadlineField);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Password");
        tableModel.addColumn("Tache Title");
        tableModel.addColumn("deadline");

        table = new JTable(tableModel);
        JScrollPane tablePane = new JScrollPane(table);
        table.setBackground(new Color(255, 255, 204));
        add(tablePane, BorderLayout.EAST);
        

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton backButton = new JButton("back");

        btnAdd.setBackground(new Color(60,179,113));
        btnAdd.setForeground(Color.white);
        btnUpdate.setBackground(new Color(0,154,255));
        btnUpdate.setForeground(Color.white);
        btnDelete.setBackground(new Color(220,20,60));
        btnDelete.setForeground(Color.white);
        backButton.setBackground(new Color(192,192,192));
        backButton.setForeground(Color.white);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(backButton);

        bottomPanel.add(buttonPanel);

        add(bottomPanel, BorderLayout.SOUTH);
        usersController.readUser(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                selectdata();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  name = nameName.getText();
                String email = emailName.getText();
                String password = passwordName.getText();
                String taches = tache.getSelectedItem().toString();
                String deadline = deadlineField.getText();
                if("".equals(name) ||"".equals(email)||"".equals(password)||"".equals(taches)||"".equals(deadline)){
                    JOptionPane.showMessageDialog(new JFrame(), "entrez touts les champs !!");
                }else {
                    usersController.ajouterUser(name, email, password, taches,deadline);
                    JOptionPane.showMessageDialog(new JFrame() ,"User ajouté avec succés");
                    usersController.readUser(tableModel);
                    tache.removeAllItems();
                    usersController.definirTache(tache);
                    clearFields();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id =Integer.valueOf( tableModel.getValueAt(selectedRow, 0).toString());
                    usersController.modifierUser(id,nameName.getText() , emailName.getText(),passwordName.getText(), tache.getSelectedItem().toString(),deadlineField.getText());  
                    JOptionPane.showMessageDialog(new JFrame() ,"User modifié avec sucssé");  
                    usersController.definirTache(tache);
                    tache.removeAllItems();
                    usersController.definirTache(tache); 
                    clearFields();              
                }else{
                    JOptionPane.showMessageDialog(new JFrame() ,"selectionée User pour modifiér !!");
                }
            }
            
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >=0) {
                        int id =Integer.valueOf( tableModel.getValueAt(selectedRow, 0).toString());
                        usersController.deleteUser(id);
                        JOptionPane.showMessageDialog(new JFrame() ,"User duprimé avec sucssé");  
                        usersController.readUser(tableModel);
                        clearFields();
                    }else{
                        JOptionPane.showMessageDialog(new JFrame() ,"selectionée User pour suprimé !!");
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
    private void selectdata() {
        if (table.getSelectedRow() >= 0) {
            int i = table.getSelectedRow();
                nameName.setText(tableModel.getValueAt(i, 1).toString());
                emailName.setText(tableModel.getValueAt(i, 2).toString());
                passwordName.setText(tableModel.getValueAt(i, 3).toString());
                tache.setSelectedItem(tableModel.getValueAt(i, 4).toString());     
                deadlineField.setText(tableModel.getValueAt(i, 5).toString());  
        }
    }

    private void clearFields() {
        nameName.setText("");
        emailName.setText("");
        passwordName.setText("");
        deadlineField.setText("");
    }
    public void back(){
        operationAdmin.setVisible(true);
        this.dispose();
    }

}