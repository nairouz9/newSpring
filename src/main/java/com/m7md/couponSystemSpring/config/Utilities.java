/**
 * 
 */
package com.m7md.couponSystemSpring.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author scary
 *
 */
public class Utilities {
	private static LocalDate localDate;
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static Date getCurrentDate() {
		localDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(localDate);
		return date;
	}

	public static Date getByMonth() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusMonths(1);
		Date date = java.sql.Date.valueOf(localDate);
		return date;
	}

	public static Date getByTwoMountsAgo() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.minusMonths(2);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	public static Date getByTwoWeeks() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusWeeks(2);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	public static Date getByWeek() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusWeeks(1);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	public static LocalDate convertDateToString(String endDate) {
		localDate = LocalDate.parse(endDate, timeFormatter);
		return localDate;
	}

}
