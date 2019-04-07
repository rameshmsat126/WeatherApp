package com.java.main.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.java.main.interfaces.WeatherServiceInterface;
import com.java.main.model.Forecast;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

	@InjectMocks
	private WeatherController weatherController;
	@Mock
	private WeatherServiceInterface weatherService;
	
	private ResponseEntity<Forecast> responseEntity;
	
	@Before
	public void setUp() {
		responseEntity=new ResponseEntity<Forecast>(HttpStatus.OK);
	}
	@Test
	public void getForcastDetails()throws Exception {
	String postalCode="85032";
		when(weatherService.getNextDayHourlyForecast(postalCode)).thenReturn(responseEntity);
		assertEquals(responseEntity, weatherController.getForcastDetails(postalCode));
		verify(weatherService.getNextDayHourlyForecast(postalCode));
	}
}
