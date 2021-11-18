package com.example.smartfarmandroidapp.MVVM.Viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;
import com.example.smartfarmandroidapp.EventsBusObject.PreferencesEvent;
import com.example.smartfarmandroidapp.MVVM.Repository.FarmSettings.ISettingsRepository;
import com.example.smartfarmandroidapp.MVVM.Repository.FarmSettings.SettingsRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class FarmSettingsViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> userID, CO2Preferred, CO2Deviation, humidityPreferred, humidityDeviation, temperaturePreferred, temperatureDeviation;
    private ISettingsRepository repository;

    public FarmSettingsViewModel(@NonNull Application application) {
        super(application);
        EventBus.getDefault().register(this);

        userID = new MutableLiveData<>();
        CO2Preferred = new MutableLiveData<>();
        CO2Deviation = new MutableLiveData<>();
        humidityPreferred = new MutableLiveData<>();
        humidityDeviation = new MutableLiveData<>();
        temperaturePreferred = new MutableLiveData<>();
        temperatureDeviation = new MutableLiveData<>();

        userID.setValue(1);

        repository = new SettingsRepository(application);
    }

    public MutableLiveData<Integer> getCO2Preferred() {
        return CO2Preferred;
    }

    public MutableLiveData<Integer> getCO2Deviation() {
        return CO2Deviation;
    }

    public MutableLiveData<Integer> getHumidityPreferred() {
        return humidityPreferred;
    }

    public MutableLiveData<Integer> getHumidityDeviation() {
        return humidityDeviation;
    }

    public MutableLiveData<Integer> getTemperaturePreferred() {
        return temperaturePreferred;
    }

    public MutableLiveData<Integer> getTemperatureDeviation() {
        return temperatureDeviation;
    }


    //TODO Bernardo, please create a wrapper
    public void savePreferences(int CO2Preferred, int CO2Deviation,
                                int temperaturePreferred, int temperatureDeviation,
                                int humidityPreferred, int humidityDeviation) {
        repository.savePreferences(new Preferences_ROOM(userID.getValue(),
                1, CO2Preferred, CO2Deviation,
                2, temperaturePreferred, temperatureDeviation,
                3, humidityPreferred, humidityDeviation));
    }

    @Subscribe
    public void onPreferencesEvent(PreferencesEvent preferencesEvent) {
        for (FarmSettingPreferences farmSettingPreference : preferencesEvent.getPreferencesArrayList()) {
            if (farmSettingPreference.getType().equals("Temperature")) {
                temperaturePreferred.postValue(farmSettingPreference.getSensorSetting().getPreferredValue());
                temperatureDeviation.postValue(farmSettingPreference.getSensorSetting().getDeviationValue());
            } else if (farmSettingPreference.getType().equals("Humidity")) {
                humidityPreferred.postValue(farmSettingPreference.getSensorSetting().getPreferredValue());
                humidityDeviation.postValue(farmSettingPreference.getSensorSetting().getDeviationValue());
            } else {
                CO2Preferred.postValue(farmSettingPreference.getSensorSetting().getPreferredValue());
                CO2Deviation.postValue(farmSettingPreference.getSensorSetting().getDeviationValue());
            }
        }
        Log.i("Preferences", "Preferences updated");
    }

    public void getPreferences() {
        repository.getPreferences(userID.getValue());
    }
}
