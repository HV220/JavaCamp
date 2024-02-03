package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            DataSource dataSource = initializeDataSource();
            initializeDatabase(dataSource);
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            System.out.println("Enter a message ID");
            Long messageId = sc.nextLong();
            Optional<Message> message = messagesRepository.findById(messageId);
            message.ifPresent(System.out::println);
        }
    }

    private static DataSource initializeDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/");
        config.setUsername("postgres");
        config.setPassword("1");
        return new HikariDataSource(config);
    }

    private static void initializeDatabase(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            String schemaScript = readScriptFile("schema.sql");
            try (Statement statement = connection.createStatement()) {
                statement.execute(schemaScript);
            }
            schemaScript = readScriptFile("data.sql");
            try (Statement statement = connection.createStatement()) {
                statement.execute(schemaScript);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String readScriptFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = Program.class.getClassLoader().getResourceAsStream(filePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
