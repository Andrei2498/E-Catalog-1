import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "dba";
    private static final String PASSWORD = "sql";
    private static Connection connection = null;
    private Database() { }
    public static Connection getConnection(){
        if(connection == null){
            createConnection();
        }
        System.out.println("Conexiune stabilita...");
        return connection;
    }

    public static void createConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL,"ECatalog","PSGBD");
        } catch (SQLException e){
            System.err.println("SQLException: " + e);
        } catch (ClassNotFoundException e){
            System.err.println("CNFException: " + e);
        }
        System.out.println("Conexiune creata...");
    }

    public static void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e){
            System.err.println("SQLException" + e);
        }
        System.out.println("Conexiune incheiata...");
    }

    public static void rollback(){
        try{
            connection.rollback();
        } catch (SQLException e){
            System.out.println("Failed rollback..");
        }
    }

    public static void commit(){
        try{
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e){
            System.out.println("Failed commit.." + e);
        }
    }
}
