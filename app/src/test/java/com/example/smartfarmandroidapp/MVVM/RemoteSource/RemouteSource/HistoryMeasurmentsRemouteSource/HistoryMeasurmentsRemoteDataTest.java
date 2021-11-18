package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.HistoryMeasurmentsRemouteSource;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.Enums.SensorEnum;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.Endpoints.HistoryMeasurementAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.HistoryMeasurementGenerator;

import junit.framework.TestCase;

import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryMeasurmentsRemoteDataTest extends TestCase {
    HistoryMeasurementGenerator historyMeasurementGenerator = new HistoryMeasurementGenerator();

    public void testGetMeasurements() {
        HistoryMeasurementAPI historyMeasurementAPI = historyMeasurementGenerator.getHistoryMeasurements();
        Call<List<Measurement>> call = historyMeasurementAPI.getHistoryMeasurement(1, SensorEnum.TEMPERATURE.getmValue());
        call.enqueue(new Callback<List<Measurement>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if(response.isSuccessful()) {
                    assertEquals(false, response.body().isEmpty());
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

            }
        });
    }
}