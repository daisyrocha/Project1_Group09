package com.example.fitnessapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is the class that holds our database.
 * Currently, our database only has one entity being held, which
 * is the User Entity.
 */
@Database(entities = {User.class}, version=2, exportSchema = false)
public abstract class FitnessAppDatabase extends RoomDatabase {
    /**
     * user() is a data access object
     * Then we are creating an instance of our FitnessApp Database
     */
    public abstract UserDao user();
    private static FitnessAppDatabase sInstance;

    /**
     * Here we are making sure we only have one instance of our database
     * If our instance is null, we create the database, but if it's
     * not null, we will return the database that is already created.
     */
    public static synchronized FitnessAppDatabase getInstance(Context context) {
        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FitnessAppDatabase.class, "fitnessapp.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    /**
     * Here I am populating my database with the first user account
     */
    public void populateInitialData() {
        if(user().count() == 0) {
            runInTransaction(() -> {
                user().addUser(new User("Administrator", "admin@csumb.edu", "Admin", "111"));
            });
        }
    }
}
