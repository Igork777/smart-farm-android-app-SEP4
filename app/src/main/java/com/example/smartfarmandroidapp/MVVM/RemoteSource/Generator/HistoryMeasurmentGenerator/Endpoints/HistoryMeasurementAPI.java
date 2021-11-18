package com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.Endpoints;

import com.example.smartfarmandroidapp.Domain.FarmSettings.FarmSettingPreferences;
import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HistoryMeasurementAPI {
    @GET("api/farms/{farmId}/sensors/{sensorId}/measurements")
    Call<List<Measurement>> getHistoryMeasurement(@Path("farmId") int farmID, @Path("sensorId") int sensorId) ;
}
