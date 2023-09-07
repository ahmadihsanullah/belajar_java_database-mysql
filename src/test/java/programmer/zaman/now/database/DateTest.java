package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Calendar;

public class DateTest {
    @Test
    void testDate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO sample_time (sample_date, sample_time,sample_timestamp) VALUES (?, ?, ?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2022);
        calendar.set(Calendar.MONTH,Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH,2);
        java.util.Date date = calendar.getTime();
        preparedStatement.setDate(1, new Date(date.getTime()));
        preparedStatement.setTime(2, new Time(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testGetDate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "SELECT * FROM sample_time";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Date date = resultSet.getDate("sample_date");
            System.out.println("Date: " + date);
            Time time = resultSet.getTime("sample_time");
            System.out.println("Time: " + time);
            Timestamp timeStamp = resultSet.getTimestamp("sample_timestamp");
            System.out.println("Timestamp: " + timeStamp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}


