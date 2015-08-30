package ch05.exercise02;

import java.time.LocalDate;

/*2. LocalDate.of(2000, 2, 29)に1年を加算すると何が起きますか.
さらに, 1年を4回加算するとどうなりますか.*/

//答案
//翌年の28日が指定され, 以後1年毎の28日が指される.
//ちなみに, 4年を加算すると4年後の29日が指される.

public class AddingYearsDemo {
	public static void main(String[] args) {
		LocalDate leapDay = LocalDate.of(2000, 2, 29);
		System.out.println(leapDay);

		LocalDate oneYearAfter = leapDay.plusYears(1);
		System.out.println(oneYearAfter);

		for (int i = 0; i < 4; i++) {
			oneYearAfter = oneYearAfter.plusYears(1);
			System.out.println(oneYearAfter);
		}

		LocalDate fourYearAfter = leapDay.plusYears(4);
		System.out.println(fourYearAfter);

		/*
		 * result ---------- 2000-02-29 2001-02-28 2002-02-28 2003-02-28
		 * 2004-02-28 2005-02-28 2004-02-29
		 */
	}
}
