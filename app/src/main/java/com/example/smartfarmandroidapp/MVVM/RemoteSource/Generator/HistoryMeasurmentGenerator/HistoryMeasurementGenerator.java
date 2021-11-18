package com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator;

import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.Endpoints.HistoryMeasurementAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.MonitorGenerator.Endpoints.LastMeasurementsAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.ServiceGenerator.ServiceGenerator;

import retrofit2.Retrofit;

public class HistoryMeasurementGenerator implements IHistoryMeasurementGenerator {
    private static HistoryMeasurementAPI historyMeasurementAPI;
    private Retrofit.Builder baseRetrofitBuilder;

    public HistoryMeasurementGenerator() {
        baseRetrofitBuilder = ServiceGenerator.getInstance();
    }



    @Override
    public HistoryMeasurementAPI getHistoryMeasurements() {
        if(historyMeasurementAPI == null)
        {
            historyMeasurementAPI = baseRetrofitBuilder
                    .build()
                    .create(HistoryMeasurementAPI.class);
        }
        return historyMeasurementAPI;
    }
}
