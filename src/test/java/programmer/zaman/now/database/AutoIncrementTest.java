package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {
    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO comments (email, comment) VALUES (?,?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, "air@gmail.com");
        preparedStatement.setString(2, "halo");

        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if(generatedKeys.next()){
            System.out.println("Comment id : " + generatedKeys.getInt(1));
        }
        generatedKeys.close();
        preparedStatement.close();
        connection.close();
    }
}
