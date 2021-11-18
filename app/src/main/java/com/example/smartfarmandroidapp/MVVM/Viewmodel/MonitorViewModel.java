package com.example.smartfarmandroidapp.MVVM.Viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.EventsBusObject.LastMeasurementsEvent;
import com.example.smartfarmandroidapp.EventsBusObject.PreferencesEvent;
import com.example.smartfarmandroidapp.MVVM.Repository.FarmSettings.ISettingsRepository;
import com.example.smartfarmandroidapp.MVVM.Repository.FarmSettings.SettingsRepository;
import com.example.smartfarmandroidapp.MVVM.Repository.Monitor.IMonitorRepository;
import com.example.smartfarmandroidapp.MVVM.Repository.Monitor.MonitorRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MonitorViewModel extends AndroidViewModel
{
    private IMonitorRepository monitorRepository;
    private ISettingsRepository settingsRepository;
    private MutableLiveData<String> CO2Level, humidity, temperature;
    private String CO2LevelString, humidityString, temperatureString;
    private MutableLiveData<String> warning;
    private String previousWarningStringBuilder ="";

    public MonitorViewModel(Application application) {
        super(application);
        EventBus.getDefault().register(this);
        // Monitor values
        CO2Level = new MutableLiveData<>();
        humidity = new MutableLiveData<>();
        temperature = new MutableLiveData<>();
        warning = new MutableLiveData<>();

        monitorRepository = new MonitorRepository(application);
        settingsRepository = new SettingsRepository(application);
    }

    public MutableLiveData<String> getCO2Level() {
        return CO2Level;
    }
    public MutableLiveData<String> getHumidity() {
        return humidity;
    }
    public MutableLiveData<String> getTemperature() {
        return temperature;
    }

    public MutableLiveData<String> getWarning() {
        return warning;
    }

    @Subscribe
    public void onLastMeasurementsEvent(LastMeasurementsEvent lastMeasurementsEvent)
    {
        String temperatureError, humidityError, CO2error;
        for (Measurement measurement: lastMeasurementsEvent.getLastMeasurements()){
            if(measurement.getMeasurementSensor().getType().equals("Temperature"))
            {
                if(!(measurement.getValue()+"").equals(temperatureString))
                {
                    temperature.postValue(measurement.getValue()+"");
                    temperatureString = measurement.getValue()+"";
                }

            }
            else if (measurement.getMeasurementSensor().getType().equals("Humidity"))
            {
                if(!(measurement.getValue()+"").equals(humidityString)) {
                    humidity.postValue(measurement.getValue() + "");
                    humidityString = measurement.getValue() + "";
                }
            }
            else
            {
                if(!(measurement.getValue()+"").equals(CO2LevelString))
                {
                    CO2Level.postValue(measurement.getValue() + "");
                    CO2LevelString = measurement.getValue() + "";
                }
            }
        }
    }


    public void fetchMeasurementData(){
       monitorRepository.getLastMeasurements();
    }

    public void fetchSettingsData(){
        settingsRepository.getPreferencesForMonitorViewModel();
    }

    @Subscribe
    public void onFetchedSettingsEvent(PreferencesEvent preferencesEvent)
    {
        FarmSettingPreferences [] correctOrderFarmSettingPreferences= new FarmSettingPreferences [3];
        for (FarmSettingPreferences farmSettingPref:preferencesEvent.getPreferencesArrayList()) {
            if(farmSettingPref.getType().equals("Temperature"))
            {
                correctOrderFarmSettingPreferences[0] = farmSettingPref;
            }
            else if (farmSettingPref.getType().equals("Humidity"))
            {
                correctOrderFarmSettingPreferences[1] = farmSettingPref;
            }
            else
            {
                correctOrderFarmSettingPreferences[2] = farmSettingPref;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        String response = "";
        for (FarmSettingPreferences farmSettingPreferences: correctOrderFarmSettingPreferences)
        {
            if(farmSettingPreferences.getType().equals("Temperature")){
               response = getNotificationMessageIfNeeded(farmSettingPreferences, temperatureString);
               if(!response.equals(""))
               {
                   stringBuilder.append("Temperature ").append(response).append("\n");
               }
            }
            else if (farmSettingPreferences.getType().equals("Humidity")){
              response = getNotificationMessageIfNeeded(farmSettingPreferences, humidityString);
                if(!response.equals(""))
                {
                    stringBuilder.append("Humidity ").append(response).append("\n");
                }
            }
            else
            {
                response = getNotificationMessageIfNeeded(farmSettingPreferences, CO2LevelString);
                if(!response.equals(""))
                {
                    stringBuilder.append("CO2 ").append(response).append("\n");
                }
            }
        }
        if(!(stringBuilder.toString().equals(previousWarningStringBuilder))) {
            warning.postValue(stringBuilder.toString());
            previousWarningStringBuilder = stringBuilder.toString();
        }
    }

    private String getNotificationMessageIfNeeded(FarmSettingPreferences farmSettingPreferences, String value_to_check_with) {
        double value = Double.parseDouble(value_to_check_with);
        int preferred = farmSettingPreferences.getSensorSetting().getPreferredValue();
        int deviation = farmSettingPreferences.getSensorSetting().getDeviationValue();
        int bottom_range = preferred - deviation;
        int highest_range = preferred + deviation;
        if(value <= bottom_range)
        {
            return "value is very low";
        }
        else if (value >= highest_range)
        {
            return "value is too high";
        }
        return "";
    }

}
