package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionPoolUtilTest {
    @Test
    void testUtil() throws Exception {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}
