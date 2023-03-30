package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory{
    private final static String USUARIO = "root";

    private final static String SENHA = "admin";

    private final static String URL = "jdbc:mysql://127.0.0.1:3306/venda_curso";

    public static Connection connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);

        }
    }

    public static void main(String[] args) {
        ConnectionFactory.connection();
    }
}