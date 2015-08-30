package ch05.exercise05;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/*
 * 5. 今までに, あなたが生きてきた日数を表示するプログラムを書きなさい.
 */
public class Cal {

	/**
	 *
	 * @param args
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) {
		System.out.println(getLivingDays(LocalDate.of(2015, 8, 31)));

	}

	public static long getLivingDays(LocalDate birthDay) {
		Objects.requireNonNull(birthDay, "A parameter date is null.");
		ChronoLocalDate today = LocalDate.now();
		if (birthDay.isAfter(today)) {
			throw new IllegalArgumentException("An illegal day is passed: future date");
		}
		return birthDay.until(today, ChronoUnit.DAYS);
	}

}
