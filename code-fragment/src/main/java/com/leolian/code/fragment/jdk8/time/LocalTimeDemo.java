/**
 * 
 */
package com.leolian.code.fragment.jdk8.time;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月24日 下午2:53:39
 */
public class LocalTimeDemo {

	public static void main(String[] args) {
		// 获取现在的时间
		LocalTime now = LocalTime.now();
		System.out.println("现在的时间: " + now);

		// 将一个字符串时间解析为LocalTime，输出15:02
		LocalTime nowTime1 = LocalTime.parse("15:02");
		System.out.println("时间是: " + nowTime1);

		// 使用静态方法of创建一个时间
		LocalTime nowTime2 = LocalTime.of(15, 02);
		System.out.println("时间是: " + nowTime2);

		// 使用解析字符串的方式并添加一小时，输出16:02
		LocalTime nextHour = LocalTime.parse("15:02").plus(1, ChronoUnit.HOURS);
		System.out.println("下一个小时: " + nextHour);

		// 获取时间的小时、分钟
		int hour = LocalTime.parse("15:02").getHour();
		System.out.println("小时: " + hour);
		int minute = LocalTime.parse("15:02").getMinute();
		System.out.println("分钟: " + minute);

		// 检查一个时间是否在另一个时间之前、之后
		boolean isBefore = LocalTime.parse("15:02").isBefore(LocalTime.parse("16:02"));
		boolean isAfter = LocalTime.parse("15:02").isAfter(LocalTime.parse("16:02"));
		System.out.println("isBefore: " + isBefore);
		System.out.println("isAfter: " + isAfter);

		// 输出 isBefore: true, isAfter: false
		System.out.println(LocalTime.MAX);
		System.out.println(LocalTime.MIN);

	}

}
