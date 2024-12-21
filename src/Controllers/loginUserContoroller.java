package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class loginUserContoroller {
    public static  int id;
        public boolean login(String email ,String password){
            String query1 = "SELECT id FROM user WHERE email =? AND password = ?";
            try(Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query1)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    id = resultSet.getInt("id");
            return true;
                }
            }
            }  catch (SQLException b){
            b.printStackTrace();
            }
            return false;
            }

        public static int getId(){
                return id ;
        }
}


