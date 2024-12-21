package Views;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controllers.TeamContrroler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamView extends JFrame {

    private JTextField teamName , memmbersName ,deadlineField;
    private JTable table;
    private DefaultTableModel tableModel;
    private operationAdmin operationAdmin ;
    private JComboBox<String> tachName;
    TeamContrroler teamController = new TeamContrroler();

    public TeamView(operationAdmin operationAdmin) {
        this.operationAdmin = operationAdmin;
        setTitle("Gestionnaire des taches");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 255));
        

        JLabel titleLabel = new JLabel("Team", JLabel.CENTER);
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.black);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Tache"));
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));
        formPanel.setPreferredSize(new Dimension(500,100));

        formPanel.add(new JLabel("Team Name:"));
        teamName = new JTextField(5);
        formPanel.add(teamName);

        formPanel.add(new JLabel("Memmbers:"));
        memmbersName = new JTextField(5);
        formPanel.add(memmbersName);

        formPanel.add(new JLabel("Tach title:"));
        tachName = new JComboBox<>();
        teamController.definirTache(tachName);
        formPanel.add(tachName);

        add(formPanel, BorderLayout.WEST);

        formPanel.add(new JLabel("Deadline:"));
        deadlineField = new JTextField(20);
        formPanel.add(deadlineField);

        formPanel.setBackground(new Color(230, 240, 255));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Team Name");
        tableModel.addColumn("Memmbers");
        tableModel.addColumn("Tach Name");
        tableModel.addColumn("Deadline");
        

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
        teamController.readteam(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                selectdata();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  name = teamName.getText();
                int members  = Integer.parseInt(memmbersName.getText());
                String titleTachName = tachName.getSelectedItem().toString();
                String deadline = deadlineField.getText();
                if("".equals(name) ||"".equals(String.valueOf(members))||"".equals(titleTachName)||"".equals(deadline)){
                    JOptionPane.showMessageDialog(new JFrame(), "entrez touts les champs !!");
                }else {
                    teamController.ajouterteam(name, members, titleTachName,deadline);
                    tachName.removeAllItems();
                    teamController.definirTache(tachName);
                    JOptionPane.showMessageDialog(new JFrame() ,"Team ajouté avec succés");
                    teamController.readteam(tableModel);
                    clearFields();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id =Integer.valueOf( tableModel.getValueAt(selectedRow, 0).toString());
                    teamController.modifierteam(id,teamName.getText() ,Integer.parseInt(memmbersName.getText().toString()),tachName.getSelectedItem().toString(),deadlineField.getText());
                    tachName.removeAllItems();
                    teamController.definirTache(tachName);   
                    JOptionPane.showMessageDialog(new JFrame() ,"Team modifié avec sucssé");  
                    teamController.readteam(tableModel);
                    clearFields();              
                }else{
                    JOptionPane.showMessageDialog(new JFrame() ,"selectionée Team pour modifiér !!");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                    if (selectedRow >=0) {
                        int id =Integer.valueOf( tableModel.getValueAt(selectedRow, 0).toString());
                        teamController.deleteteam(id);
                        JOptionPane.showMessageDialog(new JFrame() ,"Team suprimé avec sucssé");  
                        teamController.readteam(tableModel);
                        clearFields();
                    }else{
                        JOptionPane.showMessageDialog(new JFrame() ,"selectionée Team pour suprimé !!");
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
                teamName.setText(tableModel.getValueAt(i, 1).toString());
                memmbersName.setText(tableModel.getValueAt(i, 2).toString());
                tachName.setSelectedItem(tableModel.getValueAt(i, 3).toString());    
                deadlineField.setText(tableModel.getValueAt(i, 4).toString());  
        }
    }

    private void clearFields() {
        teamName.setText("");
        memmbersName.setText("");
        deadlineField.setText("");
    }
    public void back(){
        operationAdmin.setVisible(true);
        this.dispose();
    }

}