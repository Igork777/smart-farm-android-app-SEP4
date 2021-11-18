package com.example.smartfarmandroidapp.Domain.Measurments;

public class Measurement {
    private int id;

    public int getId() {
        return id;
    }

    private double value;
    public double getValue() {
        return value;
    }
    private String time;
    public String getTime()
    {
        return time;
    }
    private MeasurementSensor sensor;

    public MeasurementSensor getMeasurementSensor() {
        return sensor;
    }
}

