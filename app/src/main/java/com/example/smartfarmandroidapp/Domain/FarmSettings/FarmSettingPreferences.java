package com.example.smartfarmandroidapp.Domain.FarmSettings;

public class FarmSettingPreferences {

    private int id;
    private String type;
    private SensorSettings sensorSettings;
    public FarmSettingPreferences(int getmValue, String type, SensorSettings sensorSettings) {
        id = getmValue;
        this.type = type;
        this.sensorSetting = sensorSettings;
    }

    public void setSensorSetting(int id, String type, SensorSettings sensorSetting) {
        this.sensorSetting = sensorSetting;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    private SensorSettings sensorSetting;

    public SensorSettings getSensorSetting() {
        return sensorSetting;
    }
}
