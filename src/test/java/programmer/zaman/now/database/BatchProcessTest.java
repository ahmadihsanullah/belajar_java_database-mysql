package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessTest {
    @Test
    void testBatchStatement() throws SQLException {
        //distement saat menggunakan batch
        // dapat lebih dinamic di querynya
        // bisa diubah di perulangan
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                INSERT INTO comments (email, comment) VALUES ("ahmad@gmail.com","hai")
                """;
        for(int i = 0; i <1000; i++){
            statement.addBatch(sql); // ditampung dulu / write ke dalam memori 
        }

        statement.executeBatch(); // baru langsung kirim / flush

        statement.close();
        connection.close();
    }

    @Test
    void testBatchPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO comments (email, comment) VALUES (?,?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
        long waktuStart = System.nanoTime();

            for(int i = 0; i <1000; i++){
                preparedStatement.clearParameters(); //untuk menghapus parameter sebelumnya
                preparedStatement.setString(1, "air@gmail.com"); // lalu di set lagi
                preparedStatement.setString(2, "halo"); // set lagi
                preparedStatement.addBatch(); //ke dalam memori
                if(i%100 == 0){
                    preparedStatement.executeBatch();
                }
            }


        long waktuFinish = System.nanoTime();
        System.out.println("waktu = " + (waktuFinish - waktuStart)); // 1,4 s

        preparedStatement.close();
        connection.close();
    }
}
