package com.java.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.main.constants.Constants;
import com.java.main.interfaces.WeatherServiceInterface;
import com.java.main.model.Forecast;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	@Autowired
	private WeatherServiceInterface weatherservice;

	@Autowired
    public WeatherController(WeatherServiceInterface weatherservice) {
	this.weatherservice = weatherservice;
	}
	
	 public ResponseEntity<Forecast> getForcastDetails(@RequestParam(name = "postalCode") String postalCode) {
		LOGGER.info("getForcastDetails()::Start"); 
		Forecast foreCast=null;
		if(!StringUtils.isEmpty(postalCode) || postalCode!=null ) {
			try {
				   foreCast=weatherservice.getNextDayHourlyForecast(postalCode);
				}
			catch(Exception ex) {
				LOGGER.error(Constants.EXCEPTION_SERVICE,ex.getLocalizedMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,Constants.INTERNAL_SERVER_ERROR,ex);
			}
			return new ResponseEntity<Forecast>(foreCast,HttpStatus.OK);
		  }
	 	else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,Constants.INPUT_ERROR);
			}
	 }
}
