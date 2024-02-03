package edu.school21.chat.models;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Message {
    private Long messageId;
    private User messageAuthor;
    private Chatroom messageRoom;
    private String messageText;
    private LocalDateTime messageDateTime;


    public Message(Long messageId, String messageText, LocalDateTime messageDateTime) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.messageDateTime = messageDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        String formattedDateTime = messageDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss.SSS"));
        return "Message : {\n" +
                "  id=" + messageId +
                ",\n  author=" + messageAuthor.toString() +
                ",\n  room=" + messageRoom.toString() +
                ",\n  text='" + messageText + '\'' +
                ",\n  dateTime=" + formattedDateTime +
                "\n }";
    }


    public Message(Long messageId, User messageAuthor, Chatroom messageRoom, String messageText, LocalDateTime messageDateTime) {
        this.messageId = messageId;
        this.messageAuthor = messageAuthor;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        this.messageDateTime = messageDateTime;
    }
}
