package com.example.smartfarmandroidapp.MVVM.RoomModel.Models.Preferences;

import android.app.Application;
import android.os.AsyncTask;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.FarmSettings.SensorSettings;
import com.example.smartfarmandroidapp.Domain.Preferences.Preferences_ROOM;
import com.example.smartfarmandroidapp.EventsBusObject.PreferencesEvent;
import com.example.smartfarmandroidapp.MVVM.RoomModel.DAO.PreferencesDAO;
import com.example.smartfarmandroidapp.MVVM.RoomModel.Database.PreferencesDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PreferencesModel implements IPreferencesModel {

    private static PreferencesModel instance;
    private PreferencesDAO preferencesDAO;
    private final ExecutorService executorService;

    public PreferencesModel(Application application) {
        PreferencesDatabase preferencesDatabase = PreferencesDatabase.getInstance(application);
        preferencesDAO = preferencesDatabase.preferencesDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized PreferencesModel getInstance(Application application) {
        if (instance == null) {
            instance = new PreferencesModel(application);
        }
        return instance;
    }




    @Override
    public void savePreferences(Preferences_ROOM prefs) {
        executorService.execute(() -> new UpdatePreferencesAsync(preferencesDAO).execute(prefs));
    }

    @Override
    public void getPreferences(int id) {
        executorService.execute(() -> new GetPreferencesAsync(preferencesDAO).execute(id) );
    }


    public static class GetPreferencesAsync extends AsyncTask<Integer,Void,Void> {
        private PreferencesDAO preferencesDAO;

        public GetPreferencesAsync(PreferencesDAO preferencesDAO) {
            this.preferencesDAO = preferencesDAO;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            List<Preferences_ROOM> preferences_rooms = preferencesDAO.getPreferences(integers[0]);
            Preferences_ROOM preferences_room = preferences_rooms.get(0);

            List<FarmSettingPreferences> farmSettingPreferences = new ArrayList<>();
            farmSettingPreferences.add(new FarmSettingPreferences(preferences_room.getTemperatureID(), "Temperature", new SensorSettings(preferences_room.getDesiredTemperature(), preferences_room.getDeviationTemperature())));

            farmSettingPreferences.add(new FarmSettingPreferences(preferences_room.getHumidityID(), "Humidity", new SensorSettings(preferences_room.getDesiredHumidity(), preferences_room.getDeviationHumidity())));

            farmSettingPreferences.add(new FarmSettingPreferences(preferences_room.getCo2ID(), "Co2", new SensorSettings(preferences_room.getDesiredCO2(), preferences_room.getDeviationCO2())));
            PreferencesEvent preferencesEvent = new PreferencesEvent();
            preferencesEvent.setPreferencesList(farmSettingPreferences);
            EventBus.getDefault().post(preferencesEvent);
            return null;
        }
    }
    public static class UpdatePreferencesAsync extends AsyncTask<Preferences_ROOM,Void,Void> {
        private PreferencesDAO preferencesDAO;
        public UpdatePreferencesAsync(PreferencesDAO preferencesDAO) { this.preferencesDAO = preferencesDAO; }
        @Override
        protected Void doInBackground(Preferences_ROOM... preferences) {
            preferencesDAO.savePreferences(preferences[0]);
            return null;
        }}
}
