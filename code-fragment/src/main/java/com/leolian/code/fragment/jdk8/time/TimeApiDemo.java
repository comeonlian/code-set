/**
 * 
 */
package com.leolian.code.fragment.jdk8.time;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: 
 * @Author lianliang
 * @Date 2018年4月27日 下午5:20:58
 */
public class TimeApiDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		System.out.println(millis);
		
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant); // legacy java.util.Date
		System.out.println(legacyDate);
		
		System.out.println("----------------------------------");
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids
		
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
		// ZoneRules[currentStandardOffset=+01:00]
		// ZoneRules[currentStandardOffset=-03:00]
		
		System.out.println("----------------------------------");
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);

		System.out.println(now1.isBefore(now2)); // false

		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

		System.out.println(hoursBetween); // -3
		System.out.println(minutesBetween); // -239
		
		
		System.out.println("----------------------------------");
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late); // 23:59:59
		
		DateTimeFormatter germanFormatter = DateTimeFormatter
			.ofLocalizedTime(FormatStyle.SHORT)
			.withLocale(Locale.GERMAN);
		
		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime); // 13:37
		
		
		System.out.println("----------------------------------");
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		System.out.println(yesterday);
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek); // FRIDAY 
		
		
		System.out.println("----------------------------------");
		germanFormatter = DateTimeFormatter
			.ofLocalizedDate(FormatStyle.MEDIUM)
			.withLocale(Locale.GERMAN);

		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
		System.out.println(xmas); // 2014-12-24
		
		
		System.out.println("----------------------------------");
		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

		dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek); // WEDNESDAY

		Month month = sylvester.getMonth();
		System.out.println(month); // DECEMBER

		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay); // 1439
		
		
		System.out.println("----------------------------------");
		instant = sylvester
			.atZone(ZoneId.systemDefault())
			.toInstant();

		legacyDate = Date.from(instant);
		System.out.println(legacyDate); // Wed Dec 31 23:59:59 CET 2014
		
		
		System.out.println("----------------------------------");
		
		
	}

}
