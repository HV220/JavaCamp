package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepository;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        DataSource dataSource = initializeDataSource();
//        initializeDatabase(dataSource);
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        List<User> users = userRepository.findAll(1, 2);
        System.out.println("List of users:");
        for (User user: users) {
            System.out.println("User: " + user.toString());
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
            if (reader != null) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } else {
                System.err.println("Failed to open the file stream. Check if the file path is correct.");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
