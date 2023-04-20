package com.example.app_android_weather.Model;

public class ForecastRVModal {
    private String temperature;
    private String icon;
    private String condition;
    private String time;
    private String humidity;

    public ForecastRVModal(String temperature, String icon, String condition, String time, String humidity) {
        this.temperature = temperature;
        this.icon = icon;
        this.condition = condition;
        this.time = time;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}

