package com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.HistoryMeasurmentsRemouteSource;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.Enums.SensorEnum;
import com.example.smartfarmandroidapp.EventsBusObject.HistoryMeasurementEvent;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.Endpoints.HistoryMeasurementAPI;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.HistoryMeasurementGenerator;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.Generator.HistoryMeasurmentGenerator.IHistoryMeasurementGenerator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryMeasurmentsRemoteData implements IHistoryMeasurementsRemoteData{

    private IHistoryMeasurementGenerator historyMeasurementGenerator;

    public HistoryMeasurmentsRemoteData() {
        historyMeasurementGenerator = new HistoryMeasurementGenerator();
    }

    @Override
    public void getMeasurements(SensorEnum sensorEnum) {
        HistoryMeasurementAPI historyMeasurementAPI = historyMeasurementGenerator.getHistoryMeasurements();
        Call<List<Measurement>> call = historyMeasurementAPI.getHistoryMeasurement(1, sensorEnum.getmValue());
        call.enqueue(new Callback<List<Measurement>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if(response.isSuccessful())
                {
                    HistoryMeasurementEvent historyMeasurementEvent = new HistoryMeasurementEvent();
                    historyMeasurementEvent.setMeasurements(response.body());
                    EventBus.getDefault().post(historyMeasurementEvent);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

            }
        });
    }
}
