package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.FarmSettingsRemouteSource;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.Endpoints.PreferencesAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.SettingsGenerator;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsRemoteDataTest extends TestCase {
    private SettingsGenerator settingsGenerator = new SettingsGenerator();

    public void testGetPreferences() {
        PreferencesAPI preferencesAPI = settingsGenerator.getPreferencesAPI();
        Call<List<FarmSettingPreferences>> call = preferencesAPI.getPreferences(1);
        call.enqueue(new Callback<List<FarmSettingPreferences>>() {
            @Override
            public void onResponse(Call<List<FarmSettingPreferences>> call, Response<List<FarmSettingPreferences>> response) {
                if (response.isSuccessful()) {
                    assertEquals(false, response.body().isEmpty());
                }
            }

            @Override
            public void onFailure(Call<List<FarmSettingPreferences>> call, Throwable t) {

            }
        });
    }

    public void testSavePreferences() {
        PreferencesAPI preferencesAPI = settingsGenerator.getPreferencesAPI();
        ArrayList sensorSettings = new ArrayList();
        sensorSettings.add(1);
        sensorSettings.add(2);
        Call<ResponseBody> call = preferencesAPI.savePreferences(1, sensorSettings);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.code());
                if(response.isSuccessful()) {
                    assertEquals(true, response.body().equals(sensorSettings));
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
