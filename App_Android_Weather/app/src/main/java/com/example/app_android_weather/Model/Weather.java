package com.example.app_android_weather.Model;

public class Weather {
    private String day;         // Ngày
    private String status;      // Trạng thái thời tiết
    private String image;       // Ảnh nền thời tiết
    private String max;
    private String min;

    public Weather(){

    }
    public Weather(String day, String status, String image, String max, String min){
        this.day = day;
        this.status = status;
        this.image = image;
        this.max = max;
        this.min = min;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
