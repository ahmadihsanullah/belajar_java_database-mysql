package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetadataTest {
    @Test
    void testDatabaseMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDatabaseProductName());
        System.out.println(metaData.getDatabaseProductVersion());

        ResultSet resultSet = metaData.getTables("belajar_java_database",null, null, null);
        while(resultSet.next()){
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("TABLE : " + tableName);
        }
        connection.close();
    }

    @Test
    void testParameterMetadata() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        var sql = "INSERT INTO comments(email,comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
        System.out.println(parameterMetaData.getParameterType(1)); // mysql tidak support

        connection.close();
    }

    @Test
    void testResultSetMetadata() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for(var i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("-------------------");
            System.out.println("Name : " + resultSetMetaData.getColumnName(i));
            System.out.println("Type : " + resultSetMetaData.getColumnType(i));
            System.out.println("Type Name : " + resultSetMetaData.getColumnTypeName(i));
            if(resultSetMetaData.getColumnType(i) == Types.INTEGER){
                System.out.println("INI INTEGER");
            }
            System.out.println("-------------------\n");
        }

        statement.close();
        connection.close();
    }
}
