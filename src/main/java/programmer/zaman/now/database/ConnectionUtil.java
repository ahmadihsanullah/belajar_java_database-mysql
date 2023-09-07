package programmer.zaman.now.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {
    private static HikariDataSource dataSource;

    static{
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
        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
