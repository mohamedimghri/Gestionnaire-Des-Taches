package Controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class UsersController {
    
    public void ajouterUser(String name,String email, String password , String tachetitle,String deadline){
        String query = "INSERT INTO user (name , email , password , tachetitle , deadline ) VALUE (?,?,?,?,?)";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
        preparedStatement.setString(1 , name);
        preparedStatement.setString(2 , email);
        preparedStatement.setString(3 , password);
        preparedStatement.setString(4 , tachetitle);         
        preparedStatement.setString(5 , deadline);               
        preparedStatement.executeUpdate();
        setProriete(tachetitle);
        }catch(SQLException a){
        a.printStackTrace();
        }           

    }

public void modifierUser(int id,String name,String email, String password , String tachetitle , String deadline){
    String query3 = "UPDATE user SET name = ? , email = ?, password = ? , tachetitle = ? , deadline = ?  WHERE id = ? ";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query3)){
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, tachetitle);
        preparedStatement.setString(5, deadline);
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
        setProriete(tachetitle);
    }catch(SQLException n){
        n.printStackTrace();
    }        
}

public void deleteUser(int id){
    String query  = "DELETE FROM user WHERE id = ?";
    try(Connection connection = DbConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(query)){ 
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    }catch(SQLException c){
        c.printStackTrace();
    }
}

public void readUser(DefaultTableModel table){
    table.setRowCount(0);
    String query1 = "SELECT * FROM user";
    try(Connection connection = DbConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(query1);
    ResultSet resultSet = preparedStatement.executeQuery()){
    String name , email,password,tachetitle,deadline;
    int id ;
    while (resultSet.next()) {
        id = resultSet.getInt("id") ;
        name = resultSet.getString("name");
        email = resultSet.getString("email");
        password = resultSet.getString("password");
        tachetitle = resultSet.getString("tachetitle");
        deadline = resultSet.getString("deadline");
        table.addRow(new Object[]{id, name, email, password , tachetitle , deadline });
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
