package ch05.exercise01;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProgrammersDayDemo {
	// 1. plusDayを使用しないで, プログラマーの日を計算しなさい.
	public static void main(String[] args) {
		// LocalDate programmersDay = LocalDate.of(2015, 1, 1).plusDays(255);
		// LocalDate programmersDay = LocalDate.of(2015, 1,
		// 1).plus(Duration.ofDays(255));
		LocalDate programmersDay = LocalDate.of(2015, 1, 1).plus(255,
				ChronoUnit.DAYS);
		System.out.println(programmersDay);
	}
}
