package com.eagle.interview.IKM;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Q32 {
	public static void main(String[] args) {
		Date aDate = null;
		try {
			//m小写代表分钟，M大写代表月份
			aDate = new SimpleDateFormat("yyyy-mm-dd")
					.parse("2012-01-15");
			Calendar instance = Calendar.getInstance();
			instance.setTime(aDate);
			//Calendar.MONTH ，这是一个特殊于日历的值,格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0；
			// 最后一个月取决于一年中的月份数。所以这个值的初始值为0，所以我们用它来表示日历月份时需要加1
			System.out.print(instance.get(Calendar.DAY_OF_MONTH)
					+","+instance.get(Calendar.MONTH));

			final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			final LocalDate localDate = LocalDate.parse("2012-01-15", dateTimeFormatter);
			System.out.print(" "+localDate.getDayOfMonth()+","+localDate.getMonthValue());



		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

