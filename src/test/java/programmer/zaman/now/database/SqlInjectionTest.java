package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {
    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = '"+username + "' AND password = '" + password+ "'";
        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            System.out.println("login sukses : " + resultSet.getString("username"));
        }else{
            System.out.println("login gagal");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
