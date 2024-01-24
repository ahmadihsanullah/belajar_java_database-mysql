package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverTest {

    @Test
    void testRegister() {
        try{
            //1. create driver
           Driver mysqlDriver = new com.mysql.cj.jdbc.Driver(); //berpotensi error
           //2. register driver
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
