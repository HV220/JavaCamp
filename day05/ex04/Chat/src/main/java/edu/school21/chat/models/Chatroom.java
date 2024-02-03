package edu.school21.chat.models;



import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long chatroomId;
    private String chatroomName;
    private User chatroomOwner;
    private List<Message> messages;

    public Chatroom(Long chatroomId, String chatroomName) {
        this.chatroomId = chatroomId;
        this.chatroomName = chatroomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return Objects.equals(chatroomId, chatroom.chatroomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + chatroomId +
                ", name='" + chatroomName + '\'' +
                "}";
    }

    public Long getChatroomId() {
        return chatroomId;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public User getChatroomOwner() {
        return chatroomOwner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Chatroom(Long chatroomId, String chatroomName, User chatroomOwner, List<Message> messages) {
        this.chatroomId = chatroomId;
        this.chatroomName = chatroomName;
        this.chatroomOwner = chatroomOwner;
        this.messages = messages;
    }

    public Chatroom() {
    }

}
