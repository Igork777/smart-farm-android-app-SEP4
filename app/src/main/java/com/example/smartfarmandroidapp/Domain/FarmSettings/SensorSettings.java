package com.example.smartfarmandroidapp.Domain.FarmSettings;

public class SensorSettings {
    private int desiredValue;
    private int deviationValue;

    public SensorSettings(int desiredValue, int deviationValue) {
        this.desiredValue = desiredValue;
        this.deviationValue = deviationValue;
    }

    public int getPreferredValue() {
        return desiredValue;
    }


    public int getDeviationValue() {
        return deviationValue;
    }



}
