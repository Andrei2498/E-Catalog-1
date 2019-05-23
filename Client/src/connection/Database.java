package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "ECatalog";
    private static final String PASSWORD = "PSGBD";
    private static Connection connection = null;

    public Database(){
    }

    public static Connection getConnection(){
        if(connection == null){
            createConnection();
        }

        return connection;
    }

    public static void createConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        } catch (ClassNotFoundException e){
            System.out.println("Exceptie:  " + e);
        }
    }

    public static void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }
    }

    public static void commit(){
        try{
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e){
            System.out.println("Exceprite: " + e);
        }
    }

    public static void rollback(){
        try{
            connection.rollback();
        } catch (SQLException e){
            System.out.println("Exceptie: " + e);
        }
    }
}
