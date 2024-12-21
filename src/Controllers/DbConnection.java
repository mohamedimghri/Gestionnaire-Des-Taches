package Controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static void main(String[] args) {
    getConnection();
    }
    private static final String URL = "jdbc:mysql://localhost:3306/gestionairetache";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static  Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
