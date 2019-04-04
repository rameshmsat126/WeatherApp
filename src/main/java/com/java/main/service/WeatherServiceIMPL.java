package com.java.main.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.java.main.client.WeatherClient;
import com.java.main.exceptions.InvalidPostalCodeException;
import com.java.main.interfaces.WeatherServiceInterface;
import com.java.main.model.Forecast;
import com.java.main.model.Weather;
import com.java.main.utils.WeatherLocalDateTime;
import com.java.main.utils.WeatherTemperature;

@Service
public class WeatherServiceIMPL implements WeatherServiceInterface{
	 private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceIMPL.class);
	
	 private WeatherClient weatherClient;
	 
	@Override
	public Forecast getNextDayHourlyForecast(String postalCode) {

		
		if (StringUtils.isEmpty(postalCode)) {
            LOGGER.error("Postal code was not provided");
            throw new InvalidPostalCodeException("Postal code was not provided");
        }
        // GET forecast of next 48 hours to handle timezone offset of postal code
        Forecast forecast = weatherClient.getHourlyForecast(48, postalCode);

        if (forecast == null) {
            LOGGER.error("Postal code is invalid: {}", postalCode);
            throw new InvalidPostalCodeException(("Postal code is invalid"));
        }

        // Retrieve current local time based on postal code's timezone
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(forecast.getTimezone()));

        // Filter for the full 24 hours of weather forecast for the next day
        List<Weather> weatherList = forecast.getWeatherList().stream()
                .filter(weather -> weather.getLocalDateTime().toLocalDate().isAfter(localDateTime.toLocalDate()))
                .limit(24).sorted(new WeatherLocalDateTime()).collect(Collectors.toList());
        forecast.setWeatherList(weatherList);

        // Determine the coolest hour of the day from the next day's forecast
        LocalDateTime coolestHourOfDay = forecast.getWeatherList().stream().min(new WeatherTemperature())
                .get().getLocalDateTime();
        forecast.setCoolestHourOfDay(coolestHourOfDay);

        return forecast;

	}

}
