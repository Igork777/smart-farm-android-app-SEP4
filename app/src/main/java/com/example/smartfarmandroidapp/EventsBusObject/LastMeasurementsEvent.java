package com.example.smartfarmandroidapp.EventsBusObject;

import com.example.smartfarmandroidapp.Domain.Measurments.Measurement;

import java.util.ArrayList;
import java.util.List;

public class LastMeasurementsEvent {
    private List<Measurement> lastMeasurements;

    public LastMeasurementsEvent() {
        this.lastMeasurements = new ArrayList<>();
    }

    public List<Measurement> getLastMeasurements() {
        return lastMeasurements;
    }

    public void setLastMeasurements(List<Measurement> lastMeasurements) {
        this.lastMeasurements = lastMeasurements;
    }
}
