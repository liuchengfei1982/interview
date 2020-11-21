package com.eagle.interview.IKM;

import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Q17 {
	public static void main(String[] args) {
		final YearMonth ym1 = YearMonth.of(2015, Month.SEPTEMBER);
		final YearMonth ym2 = YearMonth.of(2016, Month.FEBRUARY);

		System.out.println(ym1.until(ym2,ChronoUnit.MONTHS)); //5
		System.out.println(ym1.minus(Period.ofMonths(4)).getMonthValue());

	}
}
