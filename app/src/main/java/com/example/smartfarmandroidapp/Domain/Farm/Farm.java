package com.example.smartfarmandroidapp.Domain.Farm;

import java.util.ArrayList;

public class Farm {
    private int id;
    private String name;
    private String location;
    private ArrayList<PlantKeeper> plantKeepers;
    private ArrayList<Sensor> sensors;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public ArrayList<PlantKeeper> getPlantKeepers() { return plantKeepers; }
    public void setPlantKeepers(ArrayList<PlantKeeper> plantKeepers) { this.plantKeepers = plantKeepers; }
    public ArrayList<Sensor> getSensors() { return sensors; }
    public void setSensors(ArrayList<Sensor> sensors) { this.sensors = sensors; }

    public class PlantKeeper {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String dateOfBirth;
        private String gender;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getDateOfBirth() { return dateOfBirth; }
        public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
    }

    public class Sensor {
        private int id;
        private String model;
        private String type;
        private String unit;
        private SensorSettings sensorSetting;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
        public SensorSettings getSensorSettings() { return sensorSetting; }
        public void setSensorSettings(SensorSettings sensorSetting) { this.sensorSetting = sensorSetting; }
    }

    public static class SensorSettings {
        private int desiredValue;
        private int deviationValue;

        public SensorSettings(int desiredValue, int deviationValue) {
            this.desiredValue = desiredValue;
            this.deviationValue = deviationValue;
        }

        public int getDesiredValue() { return desiredValue; }
        public void setDesiredValue(int desiredValue) { this.desiredValue = desiredValue; }
        public int getDeviationValue() { return deviationValue; }
        public void setDeviationValue(int deviationValue) { this.deviationValue = deviationValue; }
    }
}
