package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.FarmSettingsRemouteSource;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.FarmSettings.SensorSettings;

import java.util.List;

public interface ISettingsRemoteData {
    void getPreferences(int userID);
    void savePreferences(List <FarmSettingPreferences> sensorSettings);
}
