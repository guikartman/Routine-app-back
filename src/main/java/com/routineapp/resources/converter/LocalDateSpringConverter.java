package com.routineapp.resources.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateSpringConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String value) {
		if (value != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(value, formatter);
		} else {
			return null;
		}
	}

}
