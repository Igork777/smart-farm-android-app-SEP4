package com.example.smartfarmandroidapp.MVVM.Repository.FarmSettings;


import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;

public interface ISettingsRepository {
    void savePreferences(Preferences_ROOM preferences);
    void getPreferences(int userID);
    void getPreferencesForMonitorViewModel();
}
