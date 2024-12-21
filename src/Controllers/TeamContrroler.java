package Controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class TeamContrroler {
    
    public void ajouterteam(String name,int members, String tachetitle ,String deadline){
        String query = "INSERT INTO equipe (name , members , tachetitle ,  deadline ) VALUE (?,?,?,?)";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
        preparedStatement.setString(1 , name);
        preparedStatement.setInt(2 , members);
        preparedStatement.setString(3 , tachetitle);         
        preparedStatement.setString(4 , deadline);               
        preparedStatement.executeUpdate();
        setProriete(tachetitle);
        }catch(SQLException a){
        a.printStackTrace();
        }           

}

public void modifierteam(int id,String name,int members, String tachetitle , String deadline){
    String query3 = "UPDATE equipe SET name = ? , members = ? , tachetitle = ? , deadline = ?  WHERE id = ? ";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query3)){
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, members);
        preparedStatement.setString(3, tachetitle);
        preparedStatement.setString(4, deadline);
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
        setProriete(tachetitle);
    }catch(SQLException n){
        n.printStackTrace();
    }        
}

public void deleteteam(int id){
    String query  = "DELETE FROM equipe WHERE id = ?";
    try(Connection connection = DbConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(query)){ 
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    }catch(SQLException c){
        c.printStackTrace();
    }
}

public void readteam(DefaultTableModel table){
    table.setRowCount(0);
    String query1 = "SELECT * FROM equipe";
    try(Connection connection = DbConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(query1);
    ResultSet resultSet = preparedStatement.executeQuery()){
    String name ,tachetitle,deadline;
    int id,members ;
    while (resultSet.next()) {
        id = resultSet.getInt("id") ;
        name = resultSet.getString("name");
        members =resultSet.getInt("members");
        tachetitle = resultSet.getString("tachetitle");
        deadline = resultSet.getString("deadline");
        table.addRow(new Object[]{id, name ,members, tachetitle , deadline });
    }    
    }catch(SQLException b){
        b.printStackTrace();
    }
}
public void setProriete(String title){
    String query3 = "UPDATE tache SET  status = 'IN_PROGRESS'  WHERE title = ?" ;
    try(Connection connection = DbConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(query3)){
    preparedStatement.setString(1, title);  
    preparedStatement.executeUpdate();
    }catch(SQLException n){
            n.printStackTrace();
    
    }        
}
public void definirTache(JComboBox<String> jComboBox){
    String query1 = "SELECT title FROM tache WHERE status = 'PENDING' ";
    try(Connection connection = DbConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(query1);
    ResultSet resultSet = preparedStatement.executeQuery()){
        String title;
    while (resultSet.next()) {
        title = resultSet.getString("title");
        jComboBox.addItem(title);
    }    
    }catch(SQLException b){
        b.printStackTrace();
    }
    
}
}
