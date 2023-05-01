package com.enity;

import java.util.Date;

public class Quote {
    private int id;
    private String quote;
    private String author;
    private int userId;
    private Date created_at;

    public Quote() {

    }

    public Quote(String quote, String author, int userId, Date created_at) {
        this.quote = quote;
        this.author = author;
        this.userId = userId;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                ", userId=" + userId +
                ", created_at=" + created_at +
                '}';
    }
}
