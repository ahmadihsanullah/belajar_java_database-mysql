package programmer.zaman.now.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta");
        config.setUsername("root");
        config.setPassword("");

        //konfigurasi pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        //selama 60 detik tidak digunakan akan diclose
        config.setIdleTimeout(60_000);
        //restart ulang jika koneksi sudah lama digunakan
        config.setMaxLifetime(10  * 60_000);

       try{
           HikariDataSource dataSource = new HikariDataSource(config);
           Connection connection = dataSource.getConnection();
           //koneksi akan dikembalikan ke pool
           connection.close();
           //koneksi ditutup
           dataSource.close();
           System.out.println("Sukses koneksi");
       }catch (SQLException e){
           Assertions.fail(e.getMessage());
       }
    }
}
