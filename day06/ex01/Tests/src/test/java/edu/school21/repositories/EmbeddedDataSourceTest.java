package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.Connection;

public class EmbeddedDataSourceTest {

    private DataSource dataSource;

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDatabase = builder
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        dataSource = embeddedDatabase;
    }

    @Test
    public void testConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        assert connection != null : "Connection should not be null";
        connection.close();
    }
}
