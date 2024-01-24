package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class PrepareStatementTest {
    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            System.out.println("Login Sukses : " +resultSet.getString("username"));
        }else{
            System.out.println("Gagal Login");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
