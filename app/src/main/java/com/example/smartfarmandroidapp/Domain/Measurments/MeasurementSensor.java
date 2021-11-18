package com.example.smartfarmandroidapp.Domain.Measurments;

public class MeasurementSensor {
    private int id;

    public int getId() {
        return id;
    }

    private String model, type, unit, createdAt, updatedAt;

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getModel() {
        return model;
    }

}