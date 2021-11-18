package com.example.smartfarmandroidapp.MVVM.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;
import com.example.smartfarmandroidapp.Enums.SensorEnum;
import com.example.smartfarmandroidapp.EventsBusObject.HistoryMeasurementEvent;
import com.example.smartfarmandroidapp.MVVM.Repository.HistoryMeasurment.HistoryMeasurementRepository;
import com.example.smartfarmandroidapp.MVVM.Repository.HistoryMeasurment.IHistoryMeasurementRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoryMeasurementViewModel extends AndroidViewModel {

    private IHistoryMeasurementRepository historyMeasurementRepository;
    private MutableLiveData<List<Measurement>> measurements;

    public HistoryMeasurementViewModel(@NonNull @NotNull Application application) {
        super(application);
        historyMeasurementRepository = new HistoryMeasurementRepository(application);
        measurements = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onHistoryMeasurementEvent(HistoryMeasurementEvent historyMeasurementEvent) {
        measurements.postValue(historyMeasurementEvent.getMeasurements());
        for (int i = 0; i < historyMeasurementEvent.getMeasurements().size(); i++) {
            System.out.println(historyMeasurementEvent.getMeasurements().get(i).getValue());
        }
    }


    public void getHistoryMeasurements(SensorEnum sensorEnum) {
        historyMeasurementRepository.getMeasurements(sensorEnum);
    }

    public MutableLiveData<List<Measurement>> getHistoryMeasurements() {
        return measurements;
    }
}
