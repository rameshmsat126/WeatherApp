package com.java.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.main.constants.Constants;
import com.java.main.interfaces.WeatherServiceInterface;
import com.java.main.model.Forecast;
import com.java.main.validator.WeatherValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/weather")
@Api(value = "forecast", description = "Operations pertaining to weather forecasts")
public class WeatherController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	@Autowired
	private WeatherServiceInterface weatherservice;

	@Autowired
    public WeatherController(WeatherServiceInterface weatherservice) {
	this.weatherservice = weatherservice;
	}
	
	@ApiOperation(value = "Get next day forecast")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved weather forecast"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 415, message = "The file type uploaded is unsupported") })
	@RequestMapping(value="/forecast/{postalCode}")
	 public ResponseEntity<Forecast> getForcastDetails(@ApiParam(value = "Postal code", required = true)@PathVariable(name = "postalCode") String postalCode) {
		LOGGER.info("getForcastDetails()::Start"+postalCode); 
		Forecast foreCast=null;
		
		if(WeatherValidator.postalCodeValidation(postalCode)) {
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
