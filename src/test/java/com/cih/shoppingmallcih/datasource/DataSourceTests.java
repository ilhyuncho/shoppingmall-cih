package com.cih.shoppingmallcih.datasource;

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
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException{
        // DataSource는 applicaiton.properties에 설정된 DataSource 관련 설정을 통해서 생성된 빈이고
        // 별도 설정 없이 스프링에서 바로 사용이 가능한다
        @Cleanup
        Connection con = dataSource.getConnection();
        log.error("------------testConnection--------------");
        log.error(con);

        Assertions.assertNotNull(con);
    }
}
