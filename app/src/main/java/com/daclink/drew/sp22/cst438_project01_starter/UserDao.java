package com.example.fitnessapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("SELECT * FROM usersTable")
    List<User> getAll();

    @Query("SELECT COUNT(*) FROM usersTable")
    int count();

    @Delete
    void delete(User user);
}
