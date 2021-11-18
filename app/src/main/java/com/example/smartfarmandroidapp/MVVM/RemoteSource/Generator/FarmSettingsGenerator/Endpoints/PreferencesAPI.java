package com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.FarmSettingsGenerator.Endpoints;

import com.example.smartfarmandroidapp.Domain.FarmSettings.SensorSettings;
import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PreferencesAPI {
    @GET("api/farms/{farmId}/sensors")
    Call<List<FarmSettingPreferences>> getPreferences(@Path("farmId") int farmID);

    @PUT("api/farms/{farmId}/sensors/settings")
    Call<ResponseBody> savePreferences(@Path("farmId") int farmId,  @Body List<FarmSettingPreferences> sensorSettings);
}
