package edu.school21.chat.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository{
    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "WITH user_chatrooms AS (\n" +
                    "    SELECT\n" +
                    "        u.id AS user_id,\n" +
                    "        c.id AS chatroom_id,\n" +
                    "        c.name AS chatroom_name,\n" +
                    "        c.owner_id AS chatroom_owner\n" +
                    "    FROM\n" +
                    "        users u\n" +
                    "            LEFT JOIN users_chatrooms uc ON u.id = uc.user_id\n" +
                    "            LEFT JOIN chatrooms c ON uc.chatroom_id = c.id\n" +
                    "), creators AS (\n" +
                    "    SELECT\n" +
                    "        u.id AS user_id,\n" +
                    "        c.id AS chatroom_id,\n" +
                    "        c.name AS chatroom_name\n" +
                    "    FROM\n" +
                    "        users u\n" +
                    "            LEFT JOIN chatrooms c ON u.id = c.owner_id\n" +
                    ")\n" +
                    "\n" +
                    "SELECT\n" +
                    "    u.id AS user_id,\n" +
                    "    u.login,\n" +
                    "    u.password,\n" +
                    "    json_agg(json_build_object(\n" +
                    "            'chatroomId', cr.chatroom_id,\n" +
                    "            'chatroomName', cr.chatroom_name\n" +
                    "        )) AS created_rooms,\n" +
                    "    json_agg(json_build_object(\n" +
                    "            'chatroomId', ch.chatroom_id,\n" +
                    "            'chatroomName', ch.chatroom_name\n" +
                    "        )) AS social_rooms\n" +
                    "FROM\n" +
                    "    users u\n" +
                    "        LEFT JOIN creators cr ON u.id = cr.user_id\n" +
                    "        LEFT JOIN user_chatrooms ch ON u.id = ch.user_id AND ch.chatroom_owner != u.id\n" +
                    "GROUP BY\n" +
                    "    u.id, u.login, u.password\n" +
                    "ORDER BY\n" +
                    "    u.id\n" +
                    "LIMIT\n" +
                    "    ? OFFSET ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, size);
                statement.setInt(2, page * size);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Long user_id = resultSet.getLong("user_id");
                        String login = resultSet.getString("login");
                        String password = resultSet.getString("password");
                        String created_rooms = resultSet.getString("created_rooms");
                        String social_rooms = resultSet.getString("social_rooms");
                        List<Chatroom> createdRooms = parseJsonToChatroomList(created_rooms);
                        List<Chatroom> socialRooms = parseJsonToChatroomList(social_rooms);
                        User user = new User(user_id, login, password, createdRooms, socialRooms);
                        users.add(user);

                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<Chatroom> parseJsonToChatroomList(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<List<Chatroom>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
