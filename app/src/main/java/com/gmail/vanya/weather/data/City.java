package com.gmail.vanya.weather.data;

/**
 * Created by Vanya on 07.02.2016.
 */
public class City {
    private static String weather_type;
    private static String temperature;
    private static String pickedCity;
    private static String pickedId;

    public static String getPickedCity() {
        return pickedCity;
    }

    public static void setPickedCity(String pickedCity) {
        City.pickedCity = pickedCity;
    }

    public static String getPickedId() {
        return pickedId;
    }

    public static void setPickedId(String pickedId) {
        City.pickedId = pickedId;
    }

    public static String getTemperature() {
        return temperature;
    }

    public static void setTemperature(String temperature) {
        City.temperature = temperature;
    }

    public static String getWeather_type() {
        return weather_type;
    }

    public static void setWeather_type(String weather_type) {
        City.weather_type = weather_type;
    }
}
