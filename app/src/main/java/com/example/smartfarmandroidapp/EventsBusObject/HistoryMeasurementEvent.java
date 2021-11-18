package com.example.smartfarmandroidapp.EventsBusObject;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;

import java.util.ArrayList;
import java.util.List;

public class HistoryMeasurementEvent {
    private List<Measurement> measurements;

    public HistoryMeasurementEvent() {
        this.measurements = new ArrayList<>();
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> lastMeasurements) {
        this.measurements = lastMeasurements;
    }
}
