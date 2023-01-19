package org.zerock.b01;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
class B01ApplicationTests {

    @Autowired
    private DataSource dataSource;

//    @Test
//    void contextLoads() throws SQLException {
//        @Cleanup
//        Connection conn = dataSource.getConnection();
//
//        log.info(conn);
//
//        Assertions.assertNotNull(conn);
//    }

}
