package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Utilisateur;


public class displayUserController {
    Utilisateur user;
    String description;
    private int id = loginUserContoroller.getId();  
    public  Utilisateur dispalyUser(){
        String query1 = "SELECT name, tachetitle, deadline,email,password FROM user WHERE id =?";
        try(Connection connection = DbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query1)){
                preparedStatement.setInt(1, id );
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String title = resultSet.getString("tachetitle"); 
                String deadline = resultSet.getString("deadline");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                    user = new Utilisateur( name,email,password, deadline, title);
            } 
        
        }}catch(SQLException b){
            b.printStackTrace();
            }
            return user;
        }
        public String getDescription(){
            String query1 = "SELECT description  FROM tache WHERE title =?";
            try(Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query1)){
            preparedStatement.setString(1, dispalyUser().getTachetitle() );
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    description = resultSet.getString("description");      
                }    
            }}catch(SQLException b){
                b.printStackTrace();
            }
            return  description;
        }
        public void terminerTache(){
            String query3 = "UPDATE tache SET  status = 'COMPLETED'  WHERE title = ?" ;
            try(Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query3)){
            preparedStatement.setString(1,dispalyUser().getTachetitle() );  
            preparedStatement.executeUpdate();
            }catch(SQLException n){
                n.printStackTrace();
            }          
        }
}

