package com.example.smartfarmandroidapp.Enums;

public enum SensorEnum {

    TEMPERATURE(1, "Temperature"),
    HUMIDITY(2, "Humidity"),
    C02(3, "CO2");

    private int mValue;
    private String name;

    SensorEnum(int number, String name) {
        mValue = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getmValue() {
        return mValue;
    }
}
