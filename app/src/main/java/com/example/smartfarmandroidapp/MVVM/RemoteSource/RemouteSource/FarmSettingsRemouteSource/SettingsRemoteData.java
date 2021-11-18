package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.FarmSettingsRemouteSource;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.EventsBusObject.PreferencesEvent;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.Endpoints.PreferencesAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.ISettingsGenerator;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.SettingsGenerator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsRemoteData implements ISettingsRemoteData{
    private ISettingsGenerator settingsGenerator;

    public SettingsRemoteData() {
        settingsGenerator = new SettingsGenerator();
    }

    @Override
    public void getPreferences(int userID) {
        PreferencesAPI preferencesAPI = settingsGenerator.getPreferencesAPI();
        Call<List<FarmSettingPreferences>> call = preferencesAPI.getPreferences(1);
        call.enqueue(new Callback<List<FarmSettingPreferences>>() {
            @Override
            public void onResponse(Call<List<FarmSettingPreferences>> call, Response<List<FarmSettingPreferences>> response) {
                if (response.isSuccessful()) {
                    PreferencesEvent preferencesEvent = new PreferencesEvent();
                    preferencesEvent.setPreferencesList(response.body());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    EventBus.getDefault().post(preferencesEvent);
                }
            }

            @Override
            public void onFailure(Call<List<FarmSettingPreferences>> call, Throwable t) {

            }
        });
    }

    @Override
    public void savePreferences(List<FarmSettingPreferences> sensorSettings) {
        PreferencesAPI preferencesAPI = settingsGenerator.getPreferencesAPI();
        for (int i = 0; i <sensorSettings.size() ; i++) {
            Call<ResponseBody> call = preferencesAPI.savePreferences(1, sensorSettings);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    System.out.println(response.code());
                    if(response.isSuccessful()) {
                        PreferencesEvent preferencesEvent = new PreferencesEvent();
                        preferencesEvent.setPreferencesList(sensorSettings);
                        EventBus.getDefault().post(preferencesEvent);
                    }
                    System.out.println(response.code());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println(t);
                }
            });
        }
    }
}
