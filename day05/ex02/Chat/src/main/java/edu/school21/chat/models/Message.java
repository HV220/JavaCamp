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

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(User messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public Chatroom getMessageRoom() {
        return messageRoom;
    }

    public void setMessageRoom(Chatroom messageRoom) {
        this.messageRoom = messageRoom;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(LocalDateTime messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public Message(Long messageId, User messageAuthor, Chatroom messageRoom, String messageText, LocalDateTime messageDateTime) {
        this.messageId = messageId;
        this.messageAuthor = messageAuthor;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        this.messageDateTime = messageDateTime;
    }
}
