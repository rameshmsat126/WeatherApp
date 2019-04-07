package com.java.main.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.java.main.validator.WeatherValidator;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
	
	
	
	@Test
	public void testwithValidInput() {
		String postalCode="85032";
		boolean validatePostalCode=WeatherValidator.postalCodeValidation(postalCode);
		assertEquals(validatePostalCode, true);
	}
	@Test
	public void testwithNULLInput() {
		String postalCode="";
		boolean validatePostalCode=WeatherValidator.postalCodeValidation(postalCode);
		assertEquals(validatePostalCode, false);
	}
	@Test
	public void testwithInValid() {
		String postalCode="852012";
		boolean validatePostalCode=WeatherValidator.postalCodeValidation(postalCode);
		assertEquals(validatePostalCode, false);
	}
	@Test
	public void testwithInValidWithCharecters() {
		String postalCode="adgdfg";
		boolean validatePostalCode=WeatherValidator.postalCodeValidation(postalCode);
		assertEquals(validatePostalCode, false);
	}
}
