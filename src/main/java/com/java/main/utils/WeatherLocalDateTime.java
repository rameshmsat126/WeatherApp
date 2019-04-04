package com.java.main.utils;

import java.util.Comparator;

import com.java.main.model.Weather;


public class WeatherLocalDateTime implements Comparator<Weather> {

    @Override
    public int compare(Weather weather1, Weather weather2) {
        return weather1.getLocalDateTime().compareTo(weather2.getLocalDateTime());
    }
}