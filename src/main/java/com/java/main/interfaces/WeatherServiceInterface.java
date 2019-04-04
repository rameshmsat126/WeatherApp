package com.java.main.interfaces;

import com.java.main.model.Forecast;

public interface WeatherServiceInterface {

	public Forecast getNextDayHourlyForecast(String postalCode);

}
