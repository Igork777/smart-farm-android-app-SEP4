package com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.Endpoints;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LastMeasurementsAPI {
    @GET("api/farms/1/lastSensorsMeasurement")
    Call<List<Measurement>> getLastMeasurements();
}
