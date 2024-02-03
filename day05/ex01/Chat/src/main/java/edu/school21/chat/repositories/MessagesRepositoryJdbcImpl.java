package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    private final String MESSAGE_ID = "message_id";
    private final String AUTHOR_ID = "author_id";
    private final String ROOM_ID = "room_id";
    private final String AUTHOR_LOGIN = "author_login";
    private final String AUTHOR_PASSWORD = "author_password";
    private final String MESSAGE_DATE_TIME = "message_date_time";
    private final String MESSAGE_TEXT = "message_text";
    private final String ROOM_NAME = "room_name";

    private final String QUERYCOMMONDB = "SELECT\n" +
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

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(QUERYCOMMONDB)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Long message_id = resultSet.getLong(MESSAGE_ID);
                        Long author_id = resultSet.getLong(AUTHOR_ID);
                        String author_login = resultSet.getString(AUTHOR_LOGIN);
                        String author_password = resultSet.getString(AUTHOR_PASSWORD);
                        LocalDateTime date = resultSet.getTimestamp(MESSAGE_DATE_TIME).toLocalDateTime();
                        String text = resultSet.getString(MESSAGE_TEXT);
                        Long roomId = resultSet.getLong(ROOM_ID);
                        String roomName = resultSet.getString(ROOM_NAME);
                        return Optional.of(new Message(message_id, new User(author_id, author_login, author_password),
                                new Chatroom(roomId, roomName), text, date));
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
}
