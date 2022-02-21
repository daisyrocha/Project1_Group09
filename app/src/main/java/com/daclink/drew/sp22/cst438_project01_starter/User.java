package com.example.fitnessapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Here we are creating our Entity, also known as our database table
 * We are giving this table the name "users". Column names are
 * specified below.
 */
@Entity(tableName = "usersTable")
public class User {
    /**
     * Here we have our primary key, which is
     * generated automatically and our columns with the given names
     * Here we will store information of already existing users and
     * that of new users will be appended.
     */
     @PrimaryKey(autoGenerate = true)
     private int id;

     @ColumnInfo(name = "name")
     private String name;

     @ColumnInfo(name = "email")
     private String email;

     @ColumnInfo(name = "username")
     private String username;

     @ColumnInfo(name = "password")
     private String password;


    /**
     * This is the constructor to create a new user account
     */
     public User(String name, String email, String username, String password) {
         this.name = name;
         this.email = email;
         this.username = username;
         this.password = password;
     }

    /**
     * These are the getters and setters that will allow us
     * to set/modify variables as well as obtain the values
     * of name, email, username, and password.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
