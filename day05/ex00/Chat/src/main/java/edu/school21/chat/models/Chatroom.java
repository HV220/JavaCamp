package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long chatroomId;
    private String chatroomName;
    private User chatroomOwner;
    private List<Message> messages;

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
        return "Chatroom{" +
                "chatroomId=" + chatroomId +
                ", chatroomName='" + chatroomName + '\'' +
                ", chatroomOwner=" + chatroomOwner +
                '}';
    }
}
