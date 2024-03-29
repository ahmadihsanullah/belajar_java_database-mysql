package programmer.zaman.now.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @BeforeAll
    static void beforeAll() {
        try{
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Sukses konek ke database"); 
        }catch (SQLException e){
            Assertions.fail(e);
        }
        }

    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta";
        String username = "root";
        String password = "";

        //menggunakan try with resource database akan tertutup otomatis
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password)){
            System.out.println("Sukses konek ke database");
            // connection.close(); close secara manual
        }catch (SQLException e){
            Assertions.fail(e.getMessage());
        }
    }
}
