package com.java.main.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.java.main.config.WeatherConfiguration;
import com.java.main.model.Forecast;

@Component
public class WeatherClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);
    private static final String hourlyForecastEndpoint = "forecast/hourly";
    
    @Autowired
    private WeatherConfiguration weatherConfiguration;
    private RestTemplate restTemplate;

    @Autowired
    public WeatherClient(RestTemplate restTemplate, WeatherConfiguration weatherConfiguration) {
        this.weatherConfiguration = weatherConfiguration;
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves the weather forecast for a given amount of hours from weather
     * @param hours number of hours to retrieve weather forecast
     * @param postalCode postal code aka zip code
     * @return Forecast forecast containing weather of e
     */
    public Forecast getHourlyForecast(int hours, String postalCode) {
        String url = String
                .format("%s%s?key=%s&units=I&hours=%d&postal_code=%s", weatherConfiguration.getUrl(),
                        hourlyForecastEndpoint, weatherConfiguration.getApiKey(), hours, postalCode);
        LOGGER.info("urlWithQueryParams::"+url);
        Forecast forecast;

        try {
            forecast = restTemplate.getForObject(url, Forecast.class);
            LOGGER.info("getCoolestHourOfDay::"+forecast.getCoolestHourOfDay());
            LOGGER.info("getWeatherList::"+forecast.getWeatherList());
        } catch (Exception ex) {
            LOGGER.error("Error occurred while making external call to weather");
            throw ex;
        }

        return forecast;
    }
}
