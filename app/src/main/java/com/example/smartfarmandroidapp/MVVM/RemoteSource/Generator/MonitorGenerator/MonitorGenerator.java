package com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator;

import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.Endpoints.LastMeasurementsAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.ServiceGenerator.ServiceGenerator;

import retrofit2.Retrofit;

public class MonitorGenerator implements IMonitorGenerator{

    private static LastMeasurementsAPI lastMeasurementsAPI;
    private Retrofit.Builder baseRetrofitBuilder;

    public MonitorGenerator() {
        baseRetrofitBuilder = ServiceGenerator.getInstance();
    }


    public LastMeasurementsAPI getLastMeasurementApi(){
        if (lastMeasurementsAPI == null) {
            lastMeasurementsAPI = baseRetrofitBuilder
                    .build()
                    .create(LastMeasurementsAPI.class);
        }
        return lastMeasurementsAPI;
    }
}
