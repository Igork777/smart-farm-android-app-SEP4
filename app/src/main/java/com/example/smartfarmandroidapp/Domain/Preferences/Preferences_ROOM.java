package com.example.smartfarmandroidapp.Domain.Preferences;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "preferences_table")
public class Preferences_ROOM {
    @PrimaryKey
    int userID;
    int co2ID;
    int desiredCO2;
    int deviationCO2;
    int temperatureID;
    int desiredTemperature;
    int deviationTemperature;
    int humidityID;
    int desiredHumidity;
    int deviationHumidity;

    public Preferences_ROOM(int userID, int co2ID, int desiredCO2, int deviationCO2, int temperatureID, int desiredTemperature, int deviationTemperature, int humidityID, int desiredHumidity, int deviationHumidity) {
        this.userID = userID;
        this.co2ID = co2ID;
        this.desiredCO2 = desiredCO2;
        this.deviationCO2 = deviationCO2;
        this.temperatureID = temperatureID;
        this.desiredTemperature = desiredTemperature;
        this.deviationTemperature = deviationTemperature;
        this.humidityID = humidityID;
        this.desiredHumidity = desiredHumidity;
        this.deviationHumidity = deviationHumidity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCo2ID() {
        return co2ID;
    }

    public void setCo2ID(int co2ID) {
        this.co2ID = co2ID;
    }

    public int getDesiredCO2() {
        return desiredCO2;
    }

    public void setDesiredCO2(int desiredCO2) {
        this.desiredCO2 = desiredCO2;
    }

    public int getDeviationCO2() {
        return deviationCO2;
    }

    public void setDeviationCO2(int deviationCO2) {
        this.deviationCO2 = deviationCO2;
    }

    public int getTemperatureID() {
        return temperatureID;
    }

    public void setTemperatureID(int temperatureID) {
        this.temperatureID = temperatureID;
    }

    public int getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(int desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    public int getDeviationTemperature() {
        return deviationTemperature;
    }

    public void setDeviationTemperature(int deviationTemperature) {
        this.deviationTemperature = deviationTemperature;
    }

    public int getHumidityID() {
        return humidityID;
    }

    public void setHumidityID(int humidityID) {
        this.humidityID = humidityID;
    }

    public int getDesiredHumidity() {
        return desiredHumidity;
    }

    public void setDesiredHumidity(int desiredHumidity) {
        this.desiredHumidity = desiredHumidity;
    }

    public int getDeviationHumidity() {
        return deviationHumidity;
    }

    public void setDeviationHumidity(int deviationHumidity) {
        this.deviationHumidity = deviationHumidity;
    }
}
