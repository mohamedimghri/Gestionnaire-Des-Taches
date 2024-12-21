package Views ;
import Controllers.TachesController;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.Status;
import Models.Priorité;
public class TacheView extends JFrame {

    private JTextField titleName , descriptionName  , txtSearch;
    private JComboBox<String> prioriteCheckBox , statusChackeBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private operationAdmin operationAdmin ;

    TachesController tachesController = new TachesController();
    
    public TacheView(operationAdmin operationAdmin) {
        this.operationAdmin = operationAdmin;
        setTitle("Gestionnaire de Tâches");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 255));
        

        JLabel titleLabel = new JLabel("les Tâches", JLabel.CENTER);
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.black);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Tache"));
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));
        formPanel.setPreferredSize(new Dimension(500,100));

        formPanel.add(new JLabel("Title:"));
        titleName = new JTextField(5);
        formPanel.add(titleName);

        formPanel.add(new JLabel("Desctiption:"));
        descriptionName = new JTextField(5);
        formPanel.add(descriptionName);


        

        formPanel.add(new JLabel("Priorite:"));
        prioriteCheckBox = new JComboBox<String>(new String[]{Priorité.HIGH.toString(), Priorité.MEDIUM.toString(), Priorité.LOW.toString()});
        
        formPanel.add(prioriteCheckBox);

        formPanel.add(new JLabel("Status:"));
        statusChackeBox = new JComboBox<String>(new String[]{Status.PENDING.toString(), Status.IN_PROGRESS.toString(),Status.COMPLETED.toString()});
        formPanel.add(statusChackeBox);
        formPanel.setBackground(new Color(230, 240, 255));

        add(formPanel, BorderLayout.WEST);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Desctiption");
        tableModel.addColumn("Priorite");
        tableModel.addColumn("Status");
        
        table = new JTable(tableModel);
        JScrollPane tablePane = new JScrollPane(table);
        table.setBackground(new Color(255, 255, 204));

        add(tablePane, BorderLayout.EAST);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));
        searchPanel.add(new JLabel("ID:"));
        txtSearch = new JTextField(10);
        searchPanel.setBackground(new Color(230, 240, 255));
        searchPanel.add(txtSearch);
        JButton btnFind = new JButton("Find");
        btnFind.setForeground(Color.white);
        btnFind.setBackground(new Color(255,223,77));
        searchPanel.add(btnFind);
        bottomPanel.add(searchPanel);

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
        tachesController.readTache(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                selectdata();
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  title = titleName.getText();
                String description = descriptionName.getText();
                String priorité =prioriteCheckBox.getSelectedItem().toString();
                String status = statusChackeBox.getSelectedItem().toString();
                if("".equals(title) ||"".equals(description)||"".equals(priorité)||"".equals(status)){
                    JOptionPane.showMessageDialog(new JFrame(), "entrez touts les champs");
                }else {
                    tachesController.ajouterTache(title , description,priorité,status);
                    JOptionPane.showMessageDialog(new JFrame() ,"taches ajouté avec succés");
                    tachesController.readTache(tableModel);
                    clearFields();
                }
                
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id =Integer.valueOf( tableModel.getValueAt(selectedRow, 0).toString());
                    tachesController.modifierTache(id,titleName.getText() , descriptionName.getText(),prioriteCheckBox.getSelectedItem().toString(),statusChackeBox.getSelectedItem().toString());   
                    JOptionPane.showMessageDialog(new JFrame() ,"tache modifié avec sucssé");  
                    tachesController.readTache(tableModel);
                    clearFields();              
                }else{
                    JOptionPane.showMessageDialog(new JFrame() ,"selectionée une tache pour modifiér !!");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >=0) {
                    int id =Integer.valueOf( tableModel.getValueAt(selectedRow, 0).toString());
                    tachesController.deleteTache(id);
                    JOptionPane.showMessageDialog(new JFrame() ,"tache suprimé avec sucssé");  
                    tachesController.readTache(tableModel);
                    clearFields();
                }else{
                    JOptionPane.showMessageDialog(new JFrame() ,"selectionée une tache pour suprimé !!");
                }
                
            }
        });

        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findData();
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
                titleName.setText(tableModel.getValueAt(i, 1).toString());
                descriptionName.setText(tableModel.getValueAt(i, 2).toString());
                prioriteCheckBox.setSelectedItem(tableModel.getValueAt(i, 3).toString());
                statusChackeBox.setSelectedItem(tableModel.getValueAt(i, 4).toString());            
        }
    }

    private void findData() {
        String searchId = txtSearch.getText();
        Boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).toString().equals(searchId)) {
                titleName.setText(tableModel.getValueAt(i, 1).toString());
                descriptionName.setText(tableModel.getValueAt(i, 2).toString());
                prioriteCheckBox.setSelectedItem(tableModel.getValueAt(i, 3));
                statusChackeBox.setSelectedItem(tableModel.getValueAt(i, 4));
                found = true;
            }
        }
        if(!found){
            JOptionPane.showMessageDialog(new JFrame(), "tach not found !!!");
        }    
    }
    

    private void clearFields() {
        titleName.setText("");
        descriptionName.setText("");
        prioriteCheckBox.setSelectedIndex(0);
        statusChackeBox.setSelectedIndex(0);
        txtSearch.setText("");
    }
    public void back(){
        operationAdmin.setVisible(true);
        this.dispose();
    }
    

}