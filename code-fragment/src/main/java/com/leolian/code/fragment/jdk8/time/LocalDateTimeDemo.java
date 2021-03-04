/**
 * 
 */
package com.leolian.code.fragment.jdk8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月24日 下午2:58:42
 */
public class LocalDateTimeDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 获取当前的日期和时间
		LocalDateTime now = LocalDateTime.now();
		System.out.println("现在: " + now);

		// 使用静态方法和字符串的方式分别创建 LocalDateTime 对象
		LocalDateTime.of(2017, Month.JULY, 20, 15, 18);
		LocalDateTime.parse("2017-07-20T15:18:00");

		// 对日期和时间进行增减操作
		LocalDateTime tomorrow = now.plusDays(1);
		System.out.println("明天的这个时间: " + tomorrow);
		LocalDateTime minusTowHour = now.minusHours(2);
		System.out.println("两小时前: " + minusTowHour);

		// 提供一系列的get方法来获取特定单位
		Month month = now.getMonth();
		System.out.println("当前月份: " + month);

		// 日期时间格式化
		LocalDateTime now1 = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println("默认格式化: " + now1);
		System.out.println("自定义格式化: " + now1.format(dateTimeFormatter));
		LocalDateTime localDateTime = LocalDateTime.parse("2017-07-20 15:27:44", dateTimeFormatter);
		System.out.println("字符串转LocalDateTime: " + localDateTime);

		// 使用DateTimeFormatter的format方法将日期、时间格式化为字符串
		DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = dateTimeFormatter1.format(LocalDate.now());
		System.out.println("日期转字符串: " + dateString);

		// 给初始化的日期添加5天
		LocalDate initialDate = LocalDate.parse("2017-07-20");
		LocalDate finalDate = initialDate.plus(Period.ofDays(5));
		System.out.println("初始化日期: " + initialDate);
		System.out.println("加日期之后: " + finalDate);

		// 周期API中提供给我们可以比较两个日期的差别
		long between = ChronoUnit.DAYS.between(initialDate, finalDate);
		System.out.println("差距天数: " + between);

	}

}
