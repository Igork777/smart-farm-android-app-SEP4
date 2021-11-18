package com.example.smartfarmandroidapp.MVVM.RoomModel.Models.Preferences;


import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;

public interface IPreferencesModel {
    void savePreferences(Preferences_ROOM prefs);
    void getPreferences(int id);
}
