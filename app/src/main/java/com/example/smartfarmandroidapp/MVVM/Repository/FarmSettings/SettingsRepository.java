package com.example.smartfarmandroidapp.MVVM.Repository.FarmSettings;

import android.app.Application;
import android.util.Log;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.FarmSettings.SensorSettings;
import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;
import com.example.smartfarmandroidapp.Enums.SensorEnum;
import com.example.smartfarmandroidapp.EventsBusObject.PreferencesEvent;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.FarmSettingsRemouteSource.ISettingsRemoteData;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.FarmSettingsRemouteSource.SettingsRemoteData;
import com.example.smartfarmandroidapp.MVVM.RoomModel.Models.Preferences.IPreferencesModel;
import com.example.smartfarmandroidapp.MVVM.RoomModel.Models.Preferences.PreferencesModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SettingsRepository implements ISettingsRepository {
    private static boolean isFirstTime = true;
    private IPreferencesModel model;
    private ISettingsRemoteData remoteData;
    private int userID, CO2Preferred, CO2Deviation,
            humidityPreferred, humidityDeviation,
            temperaturePreferred, temperatureDeviation;

    public SettingsRepository(Application application) {
        EventBus.getDefault().register(this);
        model = new PreferencesModel(application);
        remoteData = new SettingsRemoteData();
    }

    @Override
    public void savePreferences(Preferences_ROOM preferences) {
        List<FarmSettingPreferences> sensorSettingsList = new ArrayList<>();
        System.out.println(preferences.getDesiredCO2());
        sensorSettingsList.add(new FarmSettingPreferences(SensorEnum.TEMPERATURE.getmValue(), SensorEnum.TEMPERATURE.getName(), new SensorSettings(preferences.getDesiredTemperature(), preferences.getDeviationTemperature())));
        sensorSettingsList.add(new FarmSettingPreferences(SensorEnum.HUMIDITY.getmValue(), SensorEnum.HUMIDITY.getName(), new SensorSettings(preferences.getDesiredHumidity(), preferences.getDeviationHumidity())));
        sensorSettingsList.add(new FarmSettingPreferences(SensorEnum.C02.getmValue(), SensorEnum.C02.getName(), new SensorSettings(preferences.getDesiredCO2(), preferences.getDeviationCO2())));


        remoteData.savePreferences(sensorSettingsList);
    }

    @Subscribe
    public void onPreferencesEvent(PreferencesEvent preferencesEvent) {
        for (FarmSettingPreferences farmSettingPreference : preferencesEvent.getPreferencesArrayList()) {
            if (farmSettingPreference.getType().equals("Temperature")) {
                temperaturePreferred = farmSettingPreference.getSensorSetting().getPreferredValue();
                temperatureDeviation = farmSettingPreference.getSensorSetting().getDeviationValue();

            } else if (farmSettingPreference.getType().equals("Humidity")) {
                humidityPreferred = farmSettingPreference.getSensorSetting().getPreferredValue();
                humidityDeviation = farmSettingPreference.getSensorSetting().getDeviationValue();
            } else {
                CO2Preferred = farmSettingPreference.getSensorSetting().getPreferredValue();
                CO2Deviation = farmSettingPreference.getSensorSetting().getDeviationValue();
            }
        }

        model.savePreferences(new Preferences_ROOM(userID, SensorEnum.C02.getmValue(), CO2Preferred, CO2Deviation,
                SensorEnum.HUMIDITY.getmValue(), humidityPreferred, humidityDeviation,
                SensorEnum.TEMPERATURE.getmValue(), temperaturePreferred, temperatureDeviation));
        Log.i("Preferences", "Preferences updated");
    }

    @Override
    public void getPreferences(int userID) {
        this.userID = userID;
        if(isFirstTime) {
            remoteData.getPreferences(userID);
            isFirstTime = false;
        }
        else
        {
            model.getPreferences(userID);
        }
    }
    @Override
    public void getPreferencesForMonitorViewModel()
    {
        remoteData.getPreferences(1);
    }
}
