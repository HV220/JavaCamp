package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT\n" +
                    "    m.id AS message_id,\n" +
                    "    m.text AS message_text,\n" +
                    "    m.date AS message_date_time,\n" +
                    "    u.id AS author_id,\n" +
                    "    u.login AS author_login,\n" +
                    "    u.password AS author_password,\n" +
                    "    c.id AS room_id,\n" +
                    "    c.name AS room_name\n" +
                    "FROM\n" +
                    "    messages m\n" +
                    "        LEFT JOIN\n" +
                    "    users u ON m.author_id = u.id\n" +
                    "        LEFT JOIN\n" +
                    "    chatrooms c ON m.room_id = c.id\n" +
                    "WHERE\n" +
                    "        m.id = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Long message_id = resultSet.getLong("message_id");
                        Long author_id = resultSet.getLong("author_id");
                        String author_login = resultSet.getString("author_login");
                        String author_password = resultSet.getString("author_password");
                        LocalDateTime date = resultSet.getTimestamp("message_date_time").toLocalDateTime();
                        String text = resultSet.getString("message_text");
                        Long roomId = resultSet.getLong("room_id");
                        String roomName = resultSet.getString("room_name");
                        return Optional.of(new Message(message_id, new User(author_id, author_login, author_password), new Chatroom(roomId, roomName), text, date));
                    } else {
                        return Optional.empty();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void save(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "insert into messages  (author_id, room_id, text, date) values\n" +
                    "(?, ?, ?, ?);";
            checkUserAndChatroom(message.getMessageAuthor(), message.getMessageRoom());
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, message.getMessageAuthor().getUserId());
                statement.setLong(2, message.getMessageRoom().getChatroomId());
                statement.setString(3, message.getMessageText());
                statement.setTimestamp(4, Timestamp.valueOf(message.getMessageDateTime()));
                statement.executeUpdate();
            }
            afterInsert(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkUserAndChatroom(User user, Chatroom chatroom) {
        if (user.getUserId() == null || chatroom.getChatroomId() == null) {
            throw new NotSavedSubEntityException("ChatRoom or User with id = null not exists");
        }
        try (Connection connection = dataSource.getConnection()) {
            String query = "select * from users where id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, user.getUserId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) {
                        throw new NotSavedSubEntityException("User with id = " + user.getUserId() + " not exists");
                    }
                }
            }
            query = "select * from chatrooms where id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, chatroom.getChatroomId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) {
                        throw new NotSavedSubEntityException("ChatRoom with id = " + chatroom.getChatroomId() + " not exists");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afterInsert(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "select id from messages where author_id = ? and room_id = ? and text = ? and date = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, message.getMessageAuthor().getUserId());
                statement.setLong(2, message.getMessageRoom().getChatroomId());
                statement.setString(3, message.getMessageText());
                statement.setTimestamp(4, Timestamp.valueOf(message.getMessageDateTime()));
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Long message_id = resultSet.getLong("id");
                        message.setMessageId(message_id);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
