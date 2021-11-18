package com.example.smartfarmandroidapp.MVVM.RoomModel.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;
import com.example.smartfarmandroidapp.MVVM.RoomModel.DAO.PreferencesDAO;

@Database(entities = {Preferences_ROOM.class}, version = 2, exportSchema = false)
public abstract class PreferencesDatabase extends RoomDatabase {
    private static PreferencesDatabase instance;
    public abstract PreferencesDAO preferencesDAO();

    public static synchronized PreferencesDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PreferencesDatabase.class, "preferences_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
