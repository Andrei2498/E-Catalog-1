package connection;

import java.sql.Connection;

public class CreateConnection {
    public static Connection connection = Database.getConnection();
}
