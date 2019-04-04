package com.java.main.utils;

import java.util.Comparator;

import com.java.main.model.Weather;


public class WeatherTemperature implements Comparator<Weather> {

    @Override
    public int compare(Weather weather1, Weather weather2) {
        if (weather1.getTemperature() < weather2.getTemperature()) {
            return -1;
        } else if (weather1.getTemperature() > weather2.getTemperature()) {
            return 1;
        }

        return 0;
    }
}
