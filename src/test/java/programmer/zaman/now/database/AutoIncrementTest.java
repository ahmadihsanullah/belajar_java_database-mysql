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

    @Test
    void testAutoIncrementStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        
        String sql = """
                INSERT INTO comments (email, comment) VALUES ('iha@gmail.com','bagus nih')
                """;
        
        statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if(generatedKeys.next()){
            System.out.println("Comment id : " + generatedKeys.getInt(1));
        }

        generatedKeys.close();
        statement.close();
        connection.close();
    }
}
