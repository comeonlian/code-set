/**
 * 
 */
package com.leolian.code.fragment.jdk8.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月24日 下午3:04:12
 */
public class CompatibleDate {

	public static void main(String[] args) {
		// Date和Instant互相转换
		Date date = Date.from(Instant.now());
		Instant instant = date.toInstant();

		// Date转换为LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		// LocalDateTime转Date
		Date date1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		// LocalDate转Date
		Date date2 = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}

}
