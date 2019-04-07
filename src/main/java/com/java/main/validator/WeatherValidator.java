package com.java.main.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.java.main.controller.WeatherController;

public class WeatherValidator {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	public static boolean postalCodeValidation(String postalCode) {
		boolean isPostalcodeValid=false;
		if(StringUtils.isEmpty(postalCode)) {
			return isPostalcodeValid;
		}
		if (postalCode.length()==5 && postalCode.matches("[0-9]+")) {
			isPostalcodeValid=true;
		}
		else {
			LOGGER.info("Invalid Postal Code");
			isPostalcodeValid=false;
		}
		return isPostalcodeValid;
		
	}

}
