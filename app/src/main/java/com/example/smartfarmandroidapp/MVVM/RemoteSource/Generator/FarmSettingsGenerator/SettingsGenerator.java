package com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator;

import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.Endpoints.PreferencesAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.ServiceGenerator.ServiceGenerator;

import retrofit2.Retrofit;

public class SettingsGenerator implements ISettingsGenerator {
    private static PreferencesAPI preferencesAPI;
    private Retrofit.Builder baseRetrofitBuilder;

    public SettingsGenerator() {
        baseRetrofitBuilder = ServiceGenerator.getInstance();
    }

    @Override
    public PreferencesAPI getPreferencesAPI() {
        if (preferencesAPI == null) {
            preferencesAPI = baseRetrofitBuilder
                    .build()
                    .create(PreferencesAPI.class);
        }
        return preferencesAPI;
    }
}
