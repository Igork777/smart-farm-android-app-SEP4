package com.example.smartfarmandroidapp.MVVM.Repository.HistoryMeasurment;

import android.app.Application;

import com.example.smartfarmandroidapp.Enums.SensorEnum;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.HistoryMeasurmentsRemouteSource.HistoryMeasurmentsRemoteData;
import com.example.smartfarmandroidapp.MVVM.RemoteSource.RemouteSource.HistoryMeasurmentsRemouteSource.IHistoryMeasurementsRemoteData;

public class HistoryMeasurementRepository implements IHistoryMeasurementRepository {
    private IHistoryMeasurementsRemoteData historyMeasurementsRemoteData;

    public HistoryMeasurementRepository(Application application) {
        historyMeasurementsRemoteData = new HistoryMeasurmentsRemoteData();
    }
    @Override
    public void getMeasurements(SensorEnum sensorEnum) {
        historyMeasurementsRemoteData.getMeasurements(sensorEnum);
    }
}
