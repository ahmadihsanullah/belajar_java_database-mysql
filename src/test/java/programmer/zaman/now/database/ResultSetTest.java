package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {
    @Test
    void testStatementWithResultSet() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM customers";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            String customerId = resultSet.getString("id");
            String customerName = resultSet.getString("name");
            String customerEmail = resultSet.getString("customers.email");

            System.out.println(String.join(", ", customerId, customerName, customerEmail));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
