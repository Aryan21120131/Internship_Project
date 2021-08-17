package com.example.internship_project;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserModel.class},version = 1)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract DaoClass getDao();
    private static DatabaseClass instance;

    static DatabaseClass getDatabase(final Context context){
        if(instance==null){
            synchronized (DatabaseClass.class){
                instance= Room.databaseBuilder(context,DatabaseClass.class,"Crew").allowMainThreadQueries().build();
            }
        }
        return instance;
    }

    public void deleteUser(final UserModel users){
        getDao().deleteCrew(users);
    }

    public void updateUser(final UserModel users){
        getDao().updateCrew(users);
    }

}
