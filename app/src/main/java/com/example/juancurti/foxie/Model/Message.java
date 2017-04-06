package com.example.juancurti.foxie.Model;

/**
 * Created by juancurti on 2/25/17.
 */

public class Message {

    int id;
    String username;
    String message;
    String date;

    public Message(int id, String username, String message, String date) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
