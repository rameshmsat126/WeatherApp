package com.java.main.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
	
	private double temperature;
    private double feelsLikeTemperature;
    private LocalDateTime localDateTime;
    
    @JsonCreator
    public Weather(@JsonProperty("temp") double temperature, @JsonProperty("app_temp") double feelsLikeTemperature,
            @JsonProperty("timestamp_local") LocalDateTime localDateTime) {
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.localDateTime = localDateTime;
    }
    
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getFeelsLikeTemperature() {
		return feelsLikeTemperature;
	}
	public void setFeelsLikeTemperature(double feelsLikeTemperature) {
		this.feelsLikeTemperature = feelsLikeTemperature;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

}
