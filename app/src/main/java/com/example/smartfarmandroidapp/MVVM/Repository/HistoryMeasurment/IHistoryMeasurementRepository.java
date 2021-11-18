package com.example.smartfarmandroidapp.MVVM.Repository.HistoryMeasurment;

import com.example.smartfarmandroidapp.Enums.SensorEnum;

public interface IHistoryMeasurementRepository {
    void getMeasurements(SensorEnum sensorEnum);
}
