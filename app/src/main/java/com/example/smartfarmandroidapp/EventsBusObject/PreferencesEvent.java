package com.example.smartfarmandroidapp.EventsBusObject;


import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;

import java.util.ArrayList;
import java.util.List;

public class PreferencesEvent {
    private List<FarmSettingPreferences> preferencesArrayList;

    public List<FarmSettingPreferences> getPreferencesArrayList() { return preferencesArrayList; }
    public void setPreferencesList(List<FarmSettingPreferences> farmSettingPreferences) { this.preferencesArrayList = farmSettingPreferences; }
}
