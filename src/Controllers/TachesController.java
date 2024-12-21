package Controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class TachesController {
public void ajouterTache(String title,String description, String priorité , String status){
                        String query = "INSERT INTO tache (title , description , priorité , status ) VALUE (?,?,?,?)";
                        try(Connection connection = DbConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(query)){
                        preparedStatement.setString(1 , title);
                        preparedStatement.setString(2 , description);
                        preparedStatement.setString(3 , priorité);
                        preparedStatement.setString(4 , status);                     
                        preparedStatement.executeUpdate();
                        }catch(SQLException a){
                        a.printStackTrace();
                        }           
        
}

public void modifierTache(int id,String title,String description, String priorité , String status  ){
        String query3 = "UPDATE tache SET title = ? , description = ? , priorité = ? , status = ?  WHERE id = ? ";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query3)){
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, priorité);
        preparedStatement.setString(4, status);
        preparedStatement.setInt(5, id);    
        preparedStatement.executeUpdate();

        }catch(SQLException n){
                n.printStackTrace();
        
        }        
}

public void deleteTache(int id){
        String query  = "DELETE FROM Tache WHERE id = ?";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){ 
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        }catch(SQLException c){
                c.printStackTrace();
        }
}
public void readTache(DefaultTableModel table){
        table.setRowCount(0);
        String query1 = "SELECT * FROM tache";
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query1);
        ResultSet resultSet = preparedStatement.executeQuery()){
                String title , description,priorité,status  ;
                int id ;
        while (resultSet.next()) {
                id = resultSet.getInt("id") ;
                title = resultSet.getString("title");
                description = resultSet.getString("description");
                priorité = resultSet.getString("priorité");
                status = resultSet.getString("status");
                
                table.addRow(new Object[]{id, title, description , priorité , status});
        }    
        }catch(SQLException b){
        b.printStackTrace();
        }
        }

}
