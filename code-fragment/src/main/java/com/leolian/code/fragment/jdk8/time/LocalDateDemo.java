/**
 * 
 */
package com.leolian.code.fragment.jdk8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.TemporalAdjusters;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月24日 下午2:47:15
 */
public class LocalDateDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 计算星期中的天
		DayOfWeek dayOfWeek = LocalDate.parse("2017-07-31").getDayOfWeek();
		System.out.println("dayOfWeek: " + dayOfWeek);

		// 计算月中的那天
		int day = LocalDate.parse("2017-07-20").getDayOfMonth();
		System.out.println("day: " + day);

		// 今年是不是闰年
		boolean leapYear = LocalDate.now().isLeapYear();
		System.out.println("是否闰年: " + leapYear);

		// 判断是否在日期之前或之后
		boolean notBefore = LocalDate.parse("2017-07-20").isBefore(LocalDate.parse("2017-07-22"));
		System.out.println("notBefore: " + notBefore);
		boolean isAfter = LocalDate.parse("2017-07-20").isAfter(LocalDate.parse("2017-07-22"));
		System.out.println("isAfter: " + isAfter);

		// 获取这个月的第一天
		LocalDate firstDayOfMonth = LocalDate.parse("2017-07-20").with(TemporalAdjusters.firstDayOfMonth());
		System.out.println("这个月的第一天: " + firstDayOfMonth);
		firstDayOfMonth = firstDayOfMonth.withDayOfMonth(1);
		System.out.println("这个月的第一天: " + firstDayOfMonth);

		// 判断今天是否是我的生日，例如我的生日是 2009-07-20
		LocalDate birthday = LocalDate.of(2009, 07, 20);
		MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
		MonthDay today = MonthDay.from(LocalDate.now());
		System.out.println("今天是否是我的生日: " + today.equals(birthdayMd));

	}

}
